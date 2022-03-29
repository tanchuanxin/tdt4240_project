package com.mygdx.learn_libgdx_box2d.controllers;

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
import com.mygdx.learn_libgdx_box2d.JumpJellyJump;
import com.mygdx.learn_libgdx_box2d.Tools.B2WorldCreator;
import com.mygdx.learn_libgdx_box2d.Tools.WorldContactListener;
import com.mygdx.learn_libgdx_box2d.views.DpadView;
import com.mygdx.learn_libgdx_box2d.views.HudView;
import com.mygdx.learn_libgdx_box2d.models.Enemy;
import com.mygdx.learn_libgdx_box2d.models.Jelly;

public class PlayController implements Screen {
    private JumpJellyJump game;
    private TextureAtlas atlas;

    // camera and overlay
    private OrthographicCamera cam;
    private FitViewport viewport;
    private HudView hudView;
    private DpadView dpadView;

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

    public PlayController(JumpJellyJump game) {
        atlas = new TextureAtlas("game_textures.pack");
        this.game = game;

        // create cam used to follow player through cam world
        cam = new OrthographicCamera();

        // create a FitViewport to maintain virtual aspect ratio of game despite window resizing
        viewport = new FitViewport(JumpJellyJump.scale(JumpJellyJump.V_WIDTH), JumpJellyJump.scale(JumpJellyJump.V_HEIGHT), cam);

        // create our game HUD for scores/level/timer info
        hudView = new HudView(game.batch);
        dpadView = new DpadView(game.batch);

        // render our map
        mapLoader = new TmxMapLoader();
        map = mapLoader.load("map/level1.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, JumpJellyJump.scale(1));

        // set camera position to be correct at the bot left corner of the map
//        cam.position.set(viewport.getWorldWidth()/2, viewport.getWorldHeight()/2, 0);
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
        // stop previous music
        music = JumpJellyJump.assetManager.get("audio/music/fun.mp3", Music.class);
        music.stop();

        // start next music
        music = JumpJellyJump.assetManager.get("audio/music/adventure.mp3", Music.class);
        music.setLooping(true);
        music.setVolume(JumpJellyJump.getVolumeLevel());
        music.play();
    }

    public TextureAtlas getAtlas() {
        return atlas;
    }

    @Override
    public void show() {

    }

    public void handleInput(float delta) {
        if (jelly.currentState != Jelly.State.DEAD || jelly.currentState != Jelly.State.WIN) {
            // jump
            if (dpadView.isJumpPressed()) {
                if (jelly.b2body.getLinearVelocity().y == 0) {
                    jelly.b2body.applyLinearImpulse(new Vector2(0,3f), jelly.b2body.getWorldCenter(), true);
                }
            }
            // move left
            if (dpadView.isLeftPressed()) {
                if (jelly.b2body.getLinearVelocity().x >= -2) {
                    jelly.b2body.applyLinearImpulse(new Vector2(-0.1f, 0), jelly.b2body.getWorldCenter(), true);
                }
            }

            // move right
            if (dpadView.isRightPressed()) {
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
            if (enemy.getX() < jelly.getX() + JumpJellyJump.scale(224)) {
                enemy.b2body.setActive(true);
            }
        }

        // update hud
        hudView.update(delta);

        // stretch gamecam to our player x coordinate
        if (jelly.currentState != Jelly.State.DEAD) {
            cam.position.x = jelly.b2body.getPosition().x;
        }

        // update gamecam with correct coordinates after changes
        boundsCheck();
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
        game.batch.setProjectionMatrix(hudView.stage.getCamera().combined);
        hudView.stage.draw();

        // set batch to draw what the camera sees
        game.batch.setProjectionMatrix(dpadView.stage.getCamera().combined);
        dpadView.stage.draw();

        // timer ran to zero
        if (hudView.isTimeUp()) {
            jelly.die();
        }

        switch(gameOver()) {
            case 1:
                game.setScreen(new GameOverController(game, hudView.getScore(), "You died"));
                dispose();
                break;
            case 2:
                game.setScreen(new GameOverController(game, hudView.getScore(), "You won"));
                dispose();
                break;
        }

    }

    public int gameOver() {
        // jelly died
        if (jelly.currentState == Jelly.State.DEAD && jelly.getStateTimer() > 3) {
            return 1;
        }
        // jelly win
        else if (jelly.currentState == Jelly.State.WIN && jelly.getStateTimer() > 3) {
            return 2;
        } else {
            return 0;
        }
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        dpadView.resize(width, height);
    }

    private void boundsCheck() {
        int mapLeft = 0;
        // The right boundary of the map (x + width)
        int mapRight = 0 + JumpJellyJump.V_WIDTH;
        // The bottom boundary of the map (y)
        int mapBottom = 0;
        // The top boundary of the map (y + height)
        int mapTop = 0 + JumpJellyJump.V_HEIGHT;
        // The camera dimensions, halved
        float cameraHalfWidth = cam.viewportWidth * .5f;
        float cameraHalfHeight = cam.viewportHeight * .5f;

        // Move camera after player as normal

        float cameraLeft = cam.position.x - cameraHalfWidth;
        float cameraRight = cam.position.x + cameraHalfWidth;
        float cameraBottom = cam.position.y - cameraHalfHeight;
        float cameraTop = cam.position.y + cameraHalfHeight;

        // Horizontal axis
        if(JumpJellyJump.V_WIDTH < cam.viewportWidth)
        {
            cam.position.x = mapRight / 2;
        }
        else if(cameraLeft <= mapLeft)
        {
            cam.position.x = mapLeft + cameraHalfWidth;
        }
        else if(cameraRight >= mapRight)
        {
            cam.position.x = mapRight - cameraHalfWidth;
        }

        // Vertical axis
        if(JumpJellyJump.V_HEIGHT < cam.viewportHeight)
        {
            cam.position.y = mapTop / 2;
        }
        else if(cameraBottom <= mapBottom)
        {
            cam.position.y = mapBottom + cameraHalfHeight;
        }
        else if(cameraTop >= mapTop)
        {
            cam.position.y = mapTop - cameraHalfHeight;
        }
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
        hudView.dispose();
    }
}
