package com.mygdx.learn_libgdx_box2d.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.learn_libgdx_box2d.LearnLibgdxBox2d;
import com.mygdx.learn_libgdx_box2d.Tools.B2WorldCreator;
import com.mygdx.learn_libgdx_box2d.Tools.WorldContactListener;
import com.mygdx.learn_libgdx_box2d.scenes.Controls;
import com.mygdx.learn_libgdx_box2d.scenes.Hud;
import com.mygdx.learn_libgdx_box2d.sprites.Enemy;
import com.mygdx.learn_libgdx_box2d.sprites.Jelly;

public class PlayScreen implements Screen {
    private LearnLibgdxBox2d game;
    private TextureAtlas atlas;

    // camera and overlay
    private OrthographicCamera cam;
    private FitViewport viewport;
    private Hud hud;
    private Controls controls;

    // map
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    // box2d
    private World world;
    private Box2DDebugRenderer b2dr;
    private B2WorldCreator creator;

    // player
    private Jelly jelly;

    // music
    private Music music;

    public PlayScreen(LearnLibgdxBox2d game) {
        atlas = new TextureAtlas("game_textures.pack");
        this.game = game;

        // create cam used to follow player through cam world
        cam = new OrthographicCamera();

        // create a FitViewport to maintain virtual aspect ratio of game despite window resizing
        viewport = new FitViewport(LearnLibgdxBox2d.scale(LearnLibgdxBox2d.V_WIDTH), LearnLibgdxBox2d.scale(LearnLibgdxBox2d.V_HEIGHT), cam);

        // create our game HUD for scores/level/timer info
        hud = new Hud(game.batch);
        controls = new Controls(game.batch);

        // render our map
        mapLoader = new TmxMapLoader();
        map = mapLoader.load("level1.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, LearnLibgdxBox2d.scale(1));

        // set camera position to be correct at the bot left corner of the map
        cam.position.set(viewport.getWorldWidth()/2, viewport.getWorldHeight()/2, 0);

        // box2d
        world = new World(new Vector2(0,-10), true);
        b2dr = new Box2DDebugRenderer();

        // create world
        creator = new B2WorldCreator(this);

        // render player
        jelly = new Jelly(this);

        // collisions
        world.setContactListener(new WorldContactListener());

        // audio
        music = LearnLibgdxBox2d.assetManager.get("audio/music/adventure.mp3", Music.class);
        music.setLooping(true);
        music.setVolume(0.05f);
        music.play();
    }

    public TextureAtlas getAtlas() {
        return atlas;
    }

    @Override
    public void show() {

    }

    public void handleInput(float delta) {
        if (jelly.currentState != Jelly.State.DEAD) {
            // jump
//            if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            if (controls.isJumpPressed()) {
                if (jelly.b2body.getLinearVelocity().y == 0) {
                    jelly.b2body.applyLinearImpulse(new Vector2(0,3f), jelly.b2body.getWorldCenter(), true);
                }
            }
            // move left
//            if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            if (controls.isLeftPressed()) {
                if (jelly.b2body.getLinearVelocity().x >= -2) {
                    jelly.b2body.applyLinearImpulse(new Vector2(-0.1f, 0), jelly.b2body.getWorldCenter(), true);
                }
            }

            // move right
//            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            if (controls.isRightPressed()) {
                if (jelly.b2body.getLinearVelocity().x <= 2) {
                    jelly.b2body.applyLinearImpulse(new Vector2(0.1f, 0), jelly.b2body.getWorldCenter(), true);
                }
            }
        }
    }

    public void update(float delta) {
        // handle user input first
        handleInput(delta);

        // advance the world
        world.step(1/60f, 6, 2);

        // update and track player position
        jelly.update(delta);

        // update fireballs
        for (Enemy enemy : creator.getFireballs()) {
            enemy.update(delta);
            if (enemy.getX() < jelly.getX() + LearnLibgdxBox2d.scale(224)) {
                enemy.b2body.setActive(true);
            }
        }

        // update hud
        hud.update(delta);

        // stretch gamecam to our player x coordinate
        if (jelly.currentState != Jelly.State.DEAD) {
            cam.position.x = jelly.b2body.getPosition().x;
        }

        // update gamecam with correct coordinates after changes
        cam.update();

        // tell renderer to only draw what the camera can see
        renderer.setView(cam);
    }

    @Override
    public void render(float delta) {
        update(delta);

        // clear screen with black
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // render game map
        renderer.render();

        // render box2d debug lines
        b2dr.render(world, cam.combined);

        // draw our game to what the game can see
        game.batch.setProjectionMatrix(cam.combined);
        game.batch.begin();
        jelly.draw(game.batch);

        for (Enemy enemy : creator.getFireballs()) {
            enemy.draw(game.batch);
        }

        game.batch.end();

        // set batch to draw what the camera sees
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();

        // set batch to draw what the camera sees
        game.batch.setProjectionMatrix(controls.stage.getCamera().combined);
        controls.stage.draw();

        if (gameOver()) {
            int score = hud.getScore();
            game.setScreen(new GameOverScreen(game, score));
            dispose();
        }
    }

    public boolean gameOver() {
        if (jelly.currentState == Jelly.State.DEAD && jelly.getStateTimer() > 3) {
            return true;
        }
        return false;
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        controls.resize(width, height);
    }

    public TiledMap getMap() {
        return map;
    }

    public World getWorld() {
        return world;
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        map.dispose();
        renderer.dispose();
        world.dispose();
        b2dr.dispose();
        hud.dispose();
    }
}
