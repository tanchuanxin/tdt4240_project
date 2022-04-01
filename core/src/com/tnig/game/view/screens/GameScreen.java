package com.tnig.game.view.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tnig.game.controller.game_maps.GameMap;
import com.tnig.game.controller.game.Game;
import com.tnig.game.controller.game.NormalGame;
import com.tnig.game.controller.managers.GameManager;
import com.tnig.game.controller.managers.ScreenManager;
import com.tnig.game.model.physics_engine.Engine;
import com.tnig.game.model.physics_engine.GameWorld;
import com.tnig.game.utillities.AssetLoader;
import com.tnig.game.view.GameRenderer;

public class GameScreen extends AbstractScreen {
    //private final Stage stage;
    private final Engine engine;
    private final SpriteBatch batch;
    private GameMap map;
    private final GameManager gameManager;
    private final GameRenderer gameRenderer;

    public GameScreen(OrthographicCamera camera, AssetLoader assetLoader, GameMap map) {
        super(camera, assetLoader);
        this.map = map; // TODO: create map classes

        batch = new SpriteBatch();
        gameRenderer = new GameRenderer(batch);

        engine = new GameWorld();

        //TODO: Could use strategy pattern here or take in as parameter to change gamemodes at runtime
        Game initializer = new NormalGame();
        gameManager = initializer.initGame(engine, gameRenderer);

        /*
        // Initialize stage for UI drawing
        stage = new Stage(new ScreenViewport(camera));
        Table table = new Table();
        Gdx.input.setInputProcessor(stage);

        Label backBtnLabel = new Label("Back", assetLoader.getManager().get(assetLoader.SKIN_PIXTHULHU_UI));
        final Button backBtn = new Button(backBtnLabel, assetLoader.getManager().get(assetLoader.SKIN_PIXTHULHU_UI));
        backBtn.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            };

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                // Change screen to map select screen
                ScreenManager.getInstance().setScreen(Screen.MAIN_MENU);
            };
        });

        // Add actors to table layout
        table.pad(50f);
        table.setFillParent(true);
        table.row().spaceBottom(20f);
        table.add(backBtn).expandX().center().fillX();

        // Add actors to stage
        stage.addActor(table); */
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        // Render game
        batch.begin();
        // TODO: IMPLEMENT
        gameManager.renderAnimatedViews();
        batch.end();

        // Update game
        engine.update(delta);
        gameManager.update(delta);


        if (gameManager.gameFinished()){
            ScreenManager.getInstance().setScreen(Screen.GAME_OVER);

        }

        //stage.act();
        //stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        //stage.getViewport().update(width, height, true);
    }

    @Override
    public void hide() {
        // Dispose this screen and its scenes
        dispose();
    }

    @Override
    public void dispose() {
        //stage.dispose();
        engine.dispose();
        gameManager.dispose();
        batch.dispose();
    }
}
