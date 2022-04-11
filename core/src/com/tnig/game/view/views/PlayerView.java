package com.tnig.game.view.views;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tnig.game.model.models.Model;
import com.tnig.game.view.AnimatedView;

import java.util.ArrayList;
import java.util.Collection;

public class PlayerView {

    public static final float FRAME_DURATION = 4f;
    private final Animation<Texture> animation;


    private Model player;

    public PlayerView(Model model, AssetManager manager) {
        this.player = model; // må nok castes når vi har en model
        Texture[] textures = getTextures(manager);
        this.animation = new Animation<Texture>(FRAME_DURATION, textures);
    }

    /**
     * gets textures for player object from sprite assets
     * @param
     */
    private Texture[] getTextures(AssetManager manager) {
        //Texture[] textures = [texture,texture.....];
        // textures.add(manager.get(filnavn på sprite, mappe));
        // textures.add(manager.get(filnavn på sprite, mappe));
        // textures.add(manager.get(filnavn på sprite, mappe));

        // tipper 4 linjer, en for hver retning player kan peke
        return null;
    }
}
