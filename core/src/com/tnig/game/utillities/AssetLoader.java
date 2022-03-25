package com.tnig.game.utillities;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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

    // Images (Splash screen, etc)
    public static final AssetDescriptor<Texture> IMG_SPLASH_SCREEN_BG = new AssetDescriptor<Texture>("images/badlogic.jpg", Texture.class);

    // Music
    // Sounds

    // Skins (For Scene2D UI)
    public static final AssetDescriptor<Skin> SKIN_PIXTHULHU_UI = new AssetDescriptor<Skin>("skins/pixthulhu_ui/pixthulhu-ui.json", Skin.class);

    // Fonts
    public static final AssetDescriptor<FreeTypeFontGenerator> FONT_SOURCE_SANS_PRO_REGULAR = new AssetDescriptor<FreeTypeFontGenerator>("fonts/source_sans_pro_regular.ttf", FreeTypeFontGenerator.class);
    public static final AssetDescriptor<FreeTypeFontGenerator> FONT_SOURCE_SANS_PRO_REGULAR_ITALIC = new AssetDescriptor<FreeTypeFontGenerator>("fonts/source_sans_pro_regular_italic.ttf", FreeTypeFontGenerator.class);
    public static final AssetDescriptor<FreeTypeFontGenerator> FONT_SOURCE_SANS_PRO_BOLD = new AssetDescriptor<FreeTypeFontGenerator>("fonts/source_sans_pro_bold.ttf", FreeTypeFontGenerator.class);
    public static final AssetDescriptor<FreeTypeFontGenerator> FONT_SOURCE_SANS_PRO_BOLD_ITALIC = new AssetDescriptor<FreeTypeFontGenerator>("fonts/source_sans_pro_bold_italic.ttf", FreeTypeFontGenerator.class);

    public AssetManager getManager() { return manager; }

    // Loads all assets
    public void loadAll() {
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

    public void dispose()
    {
        manager.dispose();
    }
}
