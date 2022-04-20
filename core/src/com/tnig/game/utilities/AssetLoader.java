package com.tnig.game.utilities;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class AssetLoader {
    private final AssetManager manager = new AssetManager();

    public AssetLoader() {
        // Let asset manager be able to handle truetype fonts
        FileHandleResolver resolver = new InternalFileHandleResolver();
        manager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
        manager.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(resolver));
    }

    // Textures (Assets)
//    public static final AssetDescriptor<Texture> TEX_PLAYER_SPRITE = new AssetDescriptor<>("images/playerSprite.png", Texture.class);
    public static final AssetDescriptor<TextureAtlas> TEXTURE_ATLAS = new AssetDescriptor<TextureAtlas>("textures/game_textures.pack", TextureAtlas.class);

    // Images (Splash screen, etc)
    public static final AssetDescriptor<Texture> IMG_SPLASH_SCREEN_BG = new AssetDescriptor<>("images/badlogic.jpg", Texture.class);

    // Music
    // Sounds

    // Skins (For Scene2D UI)
    public static final AssetDescriptor<Skin> SKIN_PIXTHULHU_UI = new AssetDescriptor<>("skins/pixthulhu_ui/pixthulhu-ui.json", Skin.class);

    // Fonts
    public static final AssetDescriptor<FreeTypeFontGenerator> FONT_SOURCE_SANS_PRO_REGULAR = new AssetDescriptor<>("fonts/source_sans_pro_regular.ttf", FreeTypeFontGenerator.class);
    public static final AssetDescriptor<FreeTypeFontGenerator> FONT_SOURCE_SANS_PRO_REGULAR_ITALIC = new AssetDescriptor<>("fonts/source_sans_pro_regular_italic.ttf", FreeTypeFontGenerator.class);
    public static final AssetDescriptor<FreeTypeFontGenerator> FONT_SOURCE_SANS_PRO_BOLD = new AssetDescriptor<>("fonts/source_sans_pro_bold.ttf", FreeTypeFontGenerator.class);
    public static final AssetDescriptor<FreeTypeFontGenerator> FONT_SOURCE_SANS_PRO_BOLD_ITALIC = new AssetDescriptor<>("fonts/source_sans_pro_bold_italic.ttf", FreeTypeFontGenerator.class);

    // Loads all assets
    public void loadAll() {
        // Load textures
//        manager.load(TEX_PLAYER_SPRITE);
        manager.load(TEXTURE_ATLAS);

        // Load images
        manager.load(IMG_SPLASH_SCREEN_BG);

        // Load skins
        manager.load(SKIN_PIXTHULHU_UI);

        // Load fonts
        manager.load(FONT_SOURCE_SANS_PRO_REGULAR);
        manager.load(FONT_SOURCE_SANS_PRO_REGULAR_ITALIC);
        manager.load(FONT_SOURCE_SANS_PRO_BOLD);
        manager.load(FONT_SOURCE_SANS_PRO_BOLD_ITALIC);
    }

    // Loads a specific asset by taking in a generically typed AssetDescriptor
    public <T> void load(AssetDescriptor<T> assetDescriptor) {
        manager.load(assetDescriptor);
    }

    // Get a loaded asset from the AssetManager (generically typed)
    public <T> T get(AssetDescriptor<T> assetDescriptor) {
        return(manager.get(assetDescriptor));
    }

    // Blocks synchronously until loading of all assets in queue is complete
    public void blockTillLoadingComplete() {
        manager.finishLoading();
    }

    // Get progress of current loading task
    public float getLoadingProgress() {
        return(manager.getProgress());
    }

    // Checks whether loading is complete
    public boolean loadingComplete() {
        return(manager.update());
    }

    // Unload an asset from the AssetManager
    public <T> void unload(AssetDescriptor<T> assetDescriptor) {
        manager.unload(assetDescriptor.fileName);
    }

    public void dispose() {
        manager.dispose();
    }
}
