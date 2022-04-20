package com.tnig.game.view.model_views.players;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.tnig.game.model.models.Model;
import com.tnig.game.utilities.AssetLoader;
import com.tnig.game.view.model_views.AbstractAnimatedView;


public class PlayerView extends AbstractAnimatedView {

    private final TextureRegion playerStanding;
    private final Animation<TextureRegion> playerJump;
    private final Animation<TextureRegion> playerWin;

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
    }


    /**
     * method inherited from AbstractAnimatedView
     * renders/draws the player on the screen
      */
    @Override
    protected void renderModel(SpriteBatch batch, float x, float y, float width, float height, float time) {
        final TextureRegion currentFrame = playerStanding;

        batch.draw(currentFrame, x, y, width, height);
    }
}
