package com.tnig.game.utillities;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class AssetLoader {
    private final AssetManager manager = new AssetManager();

    // Textures (Assets)
    // public static final AssetDescriptor<Texture> someTexture = new AssetDescriptor<Texture>("images/sometexture.png", Texture.class);

    // Images (Splash screen, etc)
    public static final AssetDescriptor<Texture> BAD_LOGIC_SPLASH = new AssetDescriptor<Texture>("data/badlogic.jpg", Texture.class);

    // Music
    // public static final String music1 = "assets/music1.mp3";

    // Sounds

    // Skins (For Scene2D UI)

    // Fonts

    public AssetManager getManager() { return manager; }

    // Loads all assets
    public void loadAll() {
        manager.load(BAD_LOGIC_SPLASH);
    }

    public void dispose()
    {
        manager.dispose();
    }
}
