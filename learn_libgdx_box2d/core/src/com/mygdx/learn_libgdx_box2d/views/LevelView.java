package com.mygdx.learn_libgdx_box2d.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.learn_libgdx_box2d.JumpJellyJump;

import java.io.File;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class LevelView implements Disposable {
    public Viewport viewport;
    public Stage stage;
    boolean backPressed, levelPressed;
    private int width, height;
    private int levelSelected;
    private OrthographicCamera cam;
    private final File levelFolder;
    private ArrayList<String> levelFilenames;
    private int numCols;
    private int numRows;

    public LevelView(SpriteBatch sb) {
        cam = new OrthographicCamera();
        viewport = new FitViewport(JumpJellyJump.V_WIDTH, JumpJellyJump.V_HEIGHT, cam);
        stage = new Stage(viewport, sb);
        width = 30;
        height = 30;

        levelSelected = 0;
        levelFolder = new File("map");
        numCols = 5;
        levelFilenames = getLevelFilenames(levelFolder);
        numRows = (int) Math.ceil((float)levelFilenames.size() / (float)numCols);

        Gdx.input.setInputProcessor(stage);

        Image back = new Image(new Texture("buttons/back.png"));
        back.setSize(width, height);
        back.addListener(new InputListener(){

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                backPressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                backPressed = false;
            }
        });

        float horSpacer = (JumpJellyJump.V_WIDTH - (numCols + numCols-1)*width)/2;
        float verSpacer = (JumpJellyJump.V_HEIGHT - (numRows + numRows-1)*height)/2;

        Table table = new Table();
        table.left().bottom();

        // top padding
        table.add().height(verSpacer);
        table.row();

        for (int i=0; i<numRows; i++) {
            // left padding
            table.add().width(horSpacer);
            for (int j=0; j<numCols; j++) {
                if (i*numCols + j < levelFilenames.size()) {
                    Stack stack = levelValidCreator(i*numCols+j+1);
                    table.add(stack).size(width, height);
                } else {
                    table.add(levelLockCreator()).size(width, height);
                }

                // spacing between valid columns
                if (j < numCols-1) {
                    table.add().size(width, height);
                }
            }

            // right padding
            table.add().width(horSpacer);
            table.row();

            // spacing between valid rows
            if (i < numRows-1) {
                table.add().height(height);
                table.row();
            }
        }

        // bottom padding
        table.add().height(verSpacer);
        table.row();

        stage.addActor(table);
    }

    private Stack levelValidCreator(int id) {
        Image levelValid = new Image(new Texture("buttons/levelValid.png"));
        levelValid.setSize(width, height);

        Label levelLabel = new Label(String.valueOf(id), new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        float horSpacer = (levelValid.getWidth() - levelLabel.getWidth())/2;
        float verSpacer = (levelValid.getHeight() - levelLabel.getHeight())/2;

        Table levelLabelTable = new Table();
        levelLabelTable.left().bottom();

        levelLabelTable.add().width(horSpacer);
        levelLabelTable.add(levelLabel).size(horSpacer);
        levelLabelTable.add().width(horSpacer);
        levelLabelTable.row();

        levelLabelTable.add().height(verSpacer);
        levelLabelTable.row();

        Stack stack = new Stack();
        stack.add(levelValid);
        stack.add(levelLabelTable);
        stack.setName(String.valueOf(id));

        stack.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                levelSelected = Integer.valueOf(event.getListenerActor().getName());
                levelPressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                levelSelected = Integer.valueOf(event.getListenerActor().getName());
                levelPressed = false;
            }
        });

        return stack;
    }

    private Image levelLockCreator() {
        Image levelLock = new Image(new Texture("buttons/levelLock.png"));
        levelLock.setSize(width, height);
        return levelLock;
    }

    public boolean isBackPressed() {
        return backPressed;
    }

    public boolean isLevelPressed() {
        return levelPressed;
    }

    public int getLevelSelected() {
        return levelSelected;
    }

    private ArrayList<String> getLevelFilenames(File folder) {
        ArrayList<String> levelFilenames = new ArrayList<String>();
        for (File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                getLevelFilenames(fileEntry);
            } else {
                Pattern pattern = Pattern.compile("level[0-9]+\\.tmx");//. represents single character
                Matcher matcher = pattern.matcher(fileEntry.getName());
                boolean isLevelFile = matcher.matches();

                if (isLevelFile) {
                    levelFilenames.add(fileEntry.getName());
                }
            }
        }

        return levelFilenames;
    }


    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

}
