package com.tnig.game.view.model_views.obstacles;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.tnig.game.model.models.enums.Direction;
import com.tnig.game.model.models.interfaces.Model;
import com.tnig.game.model.models.obstacles.FireBall;
import com.tnig.game.utilities.AssetLoader;
import com.tnig.game.view.model_views.AbstractView;

public class FireballView extends AbstractView {
    private final Animation<TextureRegion> animation;
    private float stateTimer = 0f;
    private final FireBall fireBall;
    private Direction direction;

    public FireballView(Model model, AssetLoader assetLoader) {
        super(model);
        this.fireBall = (FireBall) model;
        this.direction = fireBall.getDirection();
        TextureAtlas textureAtlas = assetLoader.get(AssetLoader.TEXTURE_ATLAS);

        // Create animation texture
        Array<TextureRegion> frames = new Array<TextureRegion>();

        for (int i=0;i<4; i++) {
            frames.add(new TextureRegion(textureAtlas.findRegion("fireball16x16"), i*16, 0, 16, 16));
        }
        animation = new Animation<TextureRegion>( 0.1f, frames);
    }

    @Override
    protected void renderModel(SpriteBatch batch, float x, float y, float width, float height, float time) {
        stateTimer += time;

        TextureRegion currentFrame = animation.getKeyFrame(stateTimer, true);
        calculateSpriteDirection(currentFrame);

        batch.draw(currentFrame,x-width/2, y-height/2, width, height);
    }

    private void calculateSpriteDirection(TextureRegion currentFrame) {
        Direction direction = fireBall.getDirection();
        switch (direction) {
            case LEFT:
                if (this.direction == Direction.RIGHT) {
                    currentFrame.flip(true, false);
                    this.direction = direction;
                }
                break;
            case RIGHT:
                if (this.direction == Direction.LEFT) {
                    currentFrame.flip(true, false);
                    this.direction = direction;
                }
                break;
            case UP:
                if (this.direction == Direction.DOWN) {
                    currentFrame.flip(false, true);
                    this.direction = direction;
                }
                break;
            case DOWN:
                if (this.direction == Direction.UP) {
                    currentFrame.flip(false, true);
                    this.direction = direction;
                    break;
                }
        }
    }
}
