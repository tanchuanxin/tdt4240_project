package com.tnig.game.view.views.player;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tnig.game.model.models.Model;
import com.tnig.game.utilities.AssetLoader;
import com.tnig.game.view.AnimatedView;
import com.tnig.game.utilities.AssetLoader;
import com.tnig.game.view.views.ModelView;

import java.util.ArrayList;
import java.util.Collection;

public class PlayerView extends ModelView {

    private final Animation<Texture> animation;


    private Model player; // SKAL DENNE KANSKJE VÆRE EN PLAYER MODEL???

    public PlayerView(Model model) {
        super(model);
        this.player = model; // må nok castes når vi har en model FOR PLAYER
        Texture texture = getTexture();
        this.animation = new Animation<Texture>(FRAME_DURATION, texture);
    }

    /**
     * gets texture for player object from sprite assets
     * @param
     */
    private Texture getTexture() {
        Texture texture = new Texture("././././././android/assets/playerSprite");
        return texture;
    }

    @Override
    protected void renderModel(SpriteBatch batch, float x, float y, float width, float height, float time) {
        final Texture current_frame = animation.getKeyFrame(time, true);

        float scaledWidth = width * 1.1f;
        float scaledHeight = height * 1.1f;

        batch.draw(current_frame, x - scaledWidth / 2f, y - scaledHeight / 2f,
                scaledWidth / 2f, scaledHeight / 2f);
    }
}
