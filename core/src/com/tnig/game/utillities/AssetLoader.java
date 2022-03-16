package com.tnig.game.utillities;

import com.badlogic.gdx.assets.AssetManager;

public class AssetLoader {
    private static final AssetLoader INSTANCE = new AssetLoader();
    private final AssetManager manager = new AssetManager();

    // Textures (Assets)
    // public static final AssetDescriptor<Texture> someTexture = new AssetDescriptor<Texture>("images/sometexture.png", Texture.class);

    // Images (Splash screen, etc)

    // Music
    // public static final String music1 = "assets/music1.mp3";

    // Sounds

    // Skins (For Scene2D UI)

    // Fonts

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
