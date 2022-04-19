package com.tnig.game.view.model_views.players;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tnig.game.model.models.Model;
import com.tnig.game.utilities.AssetLoader;
import com.tnig.game.view.model_views.ModelView;


public class PlayerView extends ModelView {

    private final Animation<Texture> animation;


    /**
     * constructor
     * @param model the model this view is supposed to visualize
     */
    public PlayerView(Model model, AssetLoader assetLoader) {
        super(model);
        Texture texture = new Texture("images/playerSprite.png");
        this.animation = new Animation<>(FRAME_DURATION, texture);
    }

    /**
     * gets texture for player object from sprite assets
     */
    private Texture getTexture() {
        //TODO: Get texture from asset manager
        return new Texture("././././././android/assets/playerSprite");
    }

    /**
     * method inherited from ModelView
     * renders/draws the player on the screen
      */
    @Override
    protected void renderModel(SpriteBatch batch, float x, float y, float width, float height, float time) {
        final Texture current_frame = animation.getKeyFrame(time, true);

        batch.draw(current_frame, x - width / 2f, y - height / 2f,
                width / 2f, height / 2f);
    }
}
