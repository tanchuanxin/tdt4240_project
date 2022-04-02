package com.mygdx.learn_libgdx_box2d.views;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.learn_libgdx_box2d.JumpJellyJump;


public class HudView implements Disposable {
    public Stage stage;
    private Viewport viewport;

    private static Integer allowedTime;
    private static Integer mapTimer;
    private boolean timeUp;
    private float timeCount;
    private static Integer score;

    private Label countDownLabel;
    private static Label scoreLabel;
    private Label timeLabel;
    private Label levelLabel;
    private Label mapLabel;
    private Label jellyLabel;

    public HudView(SpriteBatch sb, int level) {
        allowedTime = 30;
        mapTimer = 30;
        timeCount = 0;
        score = 0;

        //setup the HUD viewport using a new camera seperate from our gamecam
        //define our stage using that viewport and our games spritebatch
        viewport = new FitViewport(JumpJellyJump.V_WIDTH, JumpJellyJump.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        //define a table used to organize our hud's labels
        Table table = new Table();
        table.top();
        table.setFillParent(true);



        countDownLabel = new Label(String.format("%03d", mapTimer), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scoreLabel = new Label(String.format("%06d", score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        timeLabel = new Label("TIME", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        levelLabel = new Label(String.valueOf(level), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        mapLabel = new Label("MAP", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        jellyLabel = new Label("JELLY", new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        table.add(jellyLabel).expandX().pad(10);
        table.add(mapLabel).expandX().pad(10);
        table.add(timeLabel).expandX().pad(10);
        table.row();
        table.add(scoreLabel).expandX();
        table.add(levelLabel).expandX();
        table.add(countDownLabel).expandX();

        stage.addActor(table);
    }

    public static Integer getScore() {
        return score + 100*(allowedTime- mapTimer);
    }

    public void update(float delta) {
        timeCount += delta;
        if (timeCount >= 1) {
            if (mapTimer > 0) {
                mapTimer--;
            } else {
                timeUp = true;
            }
            countDownLabel.setText(String.format("%03d", mapTimer));
            timeCount = 0;
        }
    }

    public static void addScore(int value) {
        score += value;
        scoreLabel.setText(String.format("%06d", score));
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    public boolean isTimeUp() {
        return timeUp;
    }
}
