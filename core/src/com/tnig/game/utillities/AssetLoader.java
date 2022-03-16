package com.tnig.game.utillities;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.

public class AssetLoader {
    private static final AssetLoader INSTANCE = new AssetLoader();
    private final AssetManager manager = new AssetManager();

    // Textures
    // public static final AssetDescriptor<Texture> someTexture = new AssetDescriptor<Texture>("images/sometexture.png", Texture.class);

    // Music
    // public static final String music1 = "assets/music1.mp3";

    // Sounds

    public static AssetLoader getInstance() {
        return INSTANCE;
    }

    // Loads all assets
    public void load() {

    }

    // Load textures
    public void loadTextures() {
        
    }

    public void dispose()
    {
        manager.dispose();
    }
}
