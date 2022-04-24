package com.tnig.game.view.model_views.players;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.tnig.game.model.models.enums.Direction;
import com.tnig.game.model.models.interfaces.Model;
import com.tnig.game.model.models.players.Player;
import com.tnig.game.model.models.players.PlayerState;
import com.tnig.game.utilities.AssetLoader;
import com.tnig.game.view.model_views.AbstractAnimatedView;


public class PlayerView extends AbstractAnimatedView {
    private final Player player;
    private final TextureRegion playerRunning;
    private final Animation<TextureRegion> playerJump;
    private final Animation<TextureRegion> playerWin;
    private float stateTimer;
    private PlayerState currentState;
    private PlayerState previousState;
    private boolean isFacingRight;

    /**
     * constructor
     * @param model the model this view is supposed to visualize
     */
    public PlayerView(Model model, AssetLoader assetLoader) {
        super(model);
        this.player = (Player) model;

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
        playerRunning = new TextureRegion(assetLoader.get(AssetLoader.TEXTURE_ATLAS).findRegion("jellyJump28x28"), 0,0,28,28);

        // facing left or right
        stateTimer = 0;
        currentState = PlayerState.RUNNING;
        previousState = PlayerState.RUNNING;
        isFacingRight = true;
    }

    private TextureRegion getCurrentFrame(){
        final TextureRegion currentFrame;
        // TODO need to add dying animation

        currentState = player.getState();


        switch (currentState) {
            case JUMPING:
                currentFrame = playerJump.getKeyFrame(stateTimer);
                break;
            case WIN:
                currentFrame = playerWin.getKeyFrame(stateTimer);
                break;
            case RUNNING:
            default:
                currentFrame = playerRunning;
                break;
        }
        return currentFrame;
    }

    private void calculateSpriteDirection(TextureRegion currentFrame){
        if ((player.getDirection() == Direction.LEFT || !isFacingRight) && !currentFrame.isFlipX()) {
            currentFrame.flip(true, false);
            isFacingRight = false;
        } else if ((player.getDirection() == Direction.RIGHT || isFacingRight) && currentFrame.isFlipX()) {
            currentFrame.flip(true, false);
            isFacingRight = true;
        }
    }


    /**
     * method inherited from AbstractAnimatedView
     * renders/draws the player on the screen
      */
    @Override
    protected void renderModel(SpriteBatch batch, float x, float y, float width, float height, float time) {


        TextureRegion currentFrame = getCurrentFrame();

        calculateSpriteDirection(currentFrame);

        stateTimer = currentState == previousState ? stateTimer + time : 0;
        previousState = currentState;

        batch.draw(currentFrame,x-width, y-height/4*3, width*2, height*2);
    }
}
