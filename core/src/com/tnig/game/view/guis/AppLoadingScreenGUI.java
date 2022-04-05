package com.tnig.game.view.guis;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.tnig.game.controller.managers.ScreenManager;
import com.tnig.game.controller.screens.ScreenName;
import com.tnig.game.utilities.AssetLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class AppLoadingScreenGUI extends AbstractGUI {
    private final Table table;
    private final List<String> loadingTexts = new ArrayList<>();
    private int loadingTextIdx;
    private final Label loadingTextLabel;
    private final ProgressBar loadingProgressBar;
    private final Random rand = new Random();

    public AppLoadingScreenGUI(OrthographicCamera camera, AssetLoader assetLoader) {
        super(camera, assetLoader);

        // Initialize table for layout
        table = new Table();

        // Load loading screen assets synchronously
        assetLoader.load(assetLoader.FONT_SOURCE_SANS_PRO_REGULAR);
        assetLoader.load(assetLoader.IMG_SPLASH_SCREEN_BG);
        assetLoader.load(assetLoader.SKIN_PIXTHULHU_UI);
        assetLoader.blockTillLoadingComplete();

        // Create loading texts
        loadingTexts.add("Holding down the jump button extends the duration of the jump.");
        loadingTexts.add("This game was made for the Software Architecture course in NTNU Norway!");
        loadingTexts.add("Do you think we will get an 'A' grade for this game project?");
        loadingTexts.add("This game was made with the MVC architecture in mind.");
        loadingTexts.add("Usability and modifiability are the core tenets of our code!");
        loadingTexts.add("This game never ends. Or does it? Are you good enough to find out?");
        loadingTexts.add("This project was 60% of our course grade. The horror!");
        loadingTexts.add("Click me so I can change! Just don't look while I'm changing!");
        loadingTexts.add("This game was created with libGDX. Here's a shout-out!");
        loadingTexts.add("Does anybody even read these loading texts? We are hiring!");
        loadingTexts.add("Look, I'm running out of ideas for these loading texts. Is the game not loaded yet?");
        loadingTexts.add("Work in dark mode, it attracts less bugs.");
        loadingTexts.add("Do you say ice cream, or gelato?");
        loadingTexts.add("What is the airspeed velocity of an unladen swallow?");
        loadingTexts.add("To win or not to lose, that is the question.");

        // Create loading bar
        Label loadingLabel = new Label("Loading...", assetLoader.get(assetLoader.SKIN_PIXTHULHU_UI), "subtitle");

        loadingTextIdx = rand.nextInt(loadingTexts.size());
        loadingTextLabel = new Label(loadingTexts.get(loadingTextIdx), assetLoader.get(assetLoader.SKIN_PIXTHULHU_UI));
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
        loadingProgressBar = new ProgressBar(0f, 1f, 0.05f, false, assetLoader.get(assetLoader.SKIN_PIXTHULHU_UI));

        // Add actors to table layout
        table.pad(50f);
        table.setFillParent(true);
        table.row().spaceBottom(50f).center();
        table.add(loadingLabel).center();
        table.row().spaceBottom(20f);
        table.add(loadingTextLabel).expandX().center().fillX();
        table.row();
        table.add(loadingProgressBar).expandX().center().fillX();

        // Add actors to stage
        stage.addActor(table);
    }


    @Override
    public void render(float delta) {
        // Get loading progress
        float progress = assetLoader.getLoadingProgress();

        // Update loading bar
        loadingProgressBar.updateVisualValue();
        loadingProgressBar.setValue(progress);

        // Draw GUI to screen
        super.render(delta);
    }
}
