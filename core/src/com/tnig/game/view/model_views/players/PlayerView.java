package com.tnig.game.view.model_views.players;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.tnig.game.model.models.interfaces.Model;
import com.tnig.game.utilities.AssetLoader;
import com.tnig.game.view.model_views.AbstractAnimatedView;


public class PlayerView extends AbstractAnimatedView {

    private final TextureRegion playerStanding;
    private final Animation<TextureRegion> playerJump;
    private final Animation<TextureRegion> playerWin;
    private float stateTimer;
    private enum state {STANDING, JUMPING};
    private state currentState;
    private state previousState;
    private boolean isFacingRight;

    /**
     * constructor
     * @param model the model this view is supposed to visualize
     */
    public PlayerView(Model model, AssetLoader assetLoader) {
        super(model);

        // Create animation texture
        Array<TextureRegion> frames = new Array<TextureRegion>();

        // Jump animation
        for (int i=0; i<9; i++) {
            frames.add(new TextureRegion(assetLoader.get(AssetLoader.TEXTURE_ATLAS).findRegion("jellyJump28x28"), i*28, 0, 28, 28));
        }

        playerJump = new Animation<TextureRegion>(0.1f, frames);
        frames.clear();

        // Win animation
        for (int i=0; i<9; i++) {
            frames.add(new TextureRegion(assetLoader.get(AssetLoader.TEXTURE_ATLAS).findRegion("jellyWin28x28"), i*28, 0, 28, 28));
        }
        playerWin = new Animation<TextureRegion>(0.1f, frames);
        frames.clear();

        // Standing frame
        playerStanding = new TextureRegion(assetLoader.get(AssetLoader.TEXTURE_ATLAS).findRegion("jellyJump28x28"), 0,0,28,28);

        // facing left or right
        stateTimer = 0;
        currentState = state.STANDING;
        previousState = state.STANDING;
        isFacingRight = true;
    }


    /**
     * method inherited from AbstractAnimatedView
     * renders/draws the player on the screen
      */
    @Override
    protected void renderModel(SpriteBatch batch, Model model, float x, float y, float width, float height, float time) {
        final TextureRegion currentFrame;

        // TODO need to add dying animation
        if (model.getBody().getLinearVelocity().y > 0) {
            currentState = state.JUMPING;
        } else {
            currentState = state.STANDING;
        }

        if (currentState == state.JUMPING) {
            currentFrame = playerJump.getKeyFrame(stateTimer);
        } else if (currentState == state.STANDING) {
            currentFrame = playerStanding;
        } else {
            currentFrame = playerStanding;
        }

        if ((model.getBody().getLinearVelocity().x < 0 || !isFacingRight) && !currentFrame.isFlipX()) {
            currentFrame.flip(true, false);
            isFacingRight = false;
        } else if ((model.getBody().getLinearVelocity().x > 0 || isFacingRight) && currentFrame.isFlipX()) {
            currentFrame.flip(true, false);
            isFacingRight = true;
        }


        stateTimer = currentState == previousState ? stateTimer + time : 0;
        previousState = currentState;

        batch.draw(currentFrame,x-width, y-height/4*3, width*2, height*2);
    }
}
