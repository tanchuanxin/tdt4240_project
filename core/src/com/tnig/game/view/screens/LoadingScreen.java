package com.tnig.game.view.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tnig.game.controller.Managers.ScreenManager;
import com.tnig.game.utillities.AssetLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class LoadingScreen extends AbstractScreen {
    private final Stage stage;
    private final Table table;
    private final List<String> loadingTexts = new ArrayList<>();
    private int loadingTextIdx;
    private final Label loadingTextLabel;
    private final ProgressBar loadingProgressBar;
    private final Random rand = new Random();

    public LoadingScreen(OrthographicCamera camera, AssetLoader assetLoader) {
        super(camera, assetLoader);

        // Initialize stage for UI drawing
        stage = new Stage(new ScreenViewport(camera));
        table = new Table();
        Gdx.input.setInputProcessor(stage);

        // Load loading screen assets synchronously
        assetLoader.getManager().load(assetLoader.FONT_SOURCE_SANS_PRO_REGULAR);
        assetLoader.getManager().load(assetLoader.IMG_SPLASH_SCREEN_BG);
        assetLoader.getManager().load(assetLoader.SKIN_PIXTHULHU_UI);
        assetLoader.getManager().finishLoading();

        // Create loading texts
        loadingTexts.add("Holding down the jump button extends the duration of the jump.");
        loadingTexts.add("This game was made for the Software Architecture course in NTNU Norway!");
        loadingTexts.add("Do you think we will get an 'A' grade for this game project?");
        loadingTexts.add("This game was made with the MVC architecture in mind.");
        loadingTexts.add("Usability and modifiability are the core tenets of our code!");
        loadingTexts.add("This game never ends. It is only limited by your skill level!");
        loadingTexts.add("This project was 60% of our course grade. The horror!");
        loadingTexts.add("Click me so I can change! Just don't look while I'm changing!");

        // Create loading bar
        loadingTextIdx = rand.nextInt(loadingTexts.size());
        loadingTextLabel = new Label(loadingTexts.get(loadingTextIdx), assetLoader.getManager().get(assetLoader.SKIN_PIXTHULHU_UI));
        loadingTextLabel.setAlignment(Align.center);
        loadingTextLabel.setWrap(true);
        loadingTextLabel.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                // On clicking the label text, randomly show one of the available loading texts
                int newLoadingTextIdx = rand.nextInt(loadingTexts.size());
                while (loadingTextIdx == newLoadingTextIdx) {
                    newLoadingTextIdx = rand.nextInt(loadingTexts.size());
                }
                loadingTextIdx = newLoadingTextIdx;
                loadingTextLabel.setText(loadingTexts.get(newLoadingTextIdx));
                return true;
            };
        });
        loadingProgressBar = new ProgressBar(0f, 1f, 0.05f, false, assetLoader.getManager().get(assetLoader.SKIN_PIXTHULHU_UI));

        // Add actors to table layout
        table.pad(50f);
        table.setFillParent(true);
        table.row().spaceBottom(20f);
        table.add(loadingTextLabel).expandX().center().fillX();
        table.row();
        table.add(loadingProgressBar).expandX().center().fillX();

        // Add actors to stage
        stage.addActor(table);

        // Load all assets asynchronously
        assetLoader.loadAll();
    }


    @Override
    public void render(float delta) {
        // If we are done loading, go to main menu screen.
        if (assetLoader.getManager().update()) {
            // Go to main menu screen
            ScreenManager.getInstance().setScreen(Screen.MAIN_MENU);
        }

        // Get loading progress
        float progress = assetLoader.getManager().getProgress();

        // Update loading bar
        loadingProgressBar.updateVisualValue();
        loadingProgressBar.setValue(progress);

        // Clear screen and redraw
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }

    @Override
    public void hide() {
        // Dispose this screen and its scenes
        dispose();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void dispose() {
        // Unload splash screen assets as we won't use them again
        stage.dispose();
        assetLoader.getManager().unload(assetLoader.IMG_SPLASH_SCREEN_BG.fileName);
    }
}
