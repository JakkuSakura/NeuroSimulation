//package com.mygdx.game;
//
//import com.badlogic.gdx.Application;
//import com.badlogic.gdx.ApplicationAdapter;
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.assets.AssetManager;
//import com.badlogic.gdx.assets.loaders.FileHandleResolver;
//import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
//import com.badlogic.gdx.graphics.Color;
//import com.badlogic.gdx.graphics.GL20;
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.BitmapFont;
//import com.badlogic.gdx.graphics.g2d.Sprite;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.badlogic.gdx.graphics.g2d.TextureRegion;
//import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
//import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
//import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;
//import com.badlogic.gdx.utils.Align;
//
//public class Test extends ApplicationAdapter {
//
//    static final String TAG = "MyGdxGame";
//
//    int screenWidth, screenHeight;
//    int iconWidth = 128;
//    int iconHeight = 256;
//    int padding = 100;
//
//    AssetManager manager;
//
//    SpriteBatch batch;
//    Texture texture;
//    TextureRegion textureRegion;
//    Sprite sprite;
//
//    BitmapFont font;
//    String appType = "";
//    String fontTestStr = "中央定调一件大事：拆除住宅小区围墙";
//    String fontFile = "HiraginoSansGBW3.otf";
//    String ttfFontTestStr = "这些文字来自字体文件，苹果丽黑：" + fontFile;
//
//    FreeTypeFontGenerator fontGenerator;
//    BitmapFont ttfFont;
//
//
//    public class Test {
//    }
//
//    public void create() {
//
//        screenWidth = Gdx.graphics.getWidth();
//        screenHeight = Gdx.graphics.getHeight();
//
//        Gdx.app.log(TAG, "create screenWidth: " + screenWidth + " screenHeight: " + screenHeight);
//
//        if (Gdx.app.getType() == Application.ApplicationType.Desktop)
//            appType = "桌面";
//        else if (Gdx.app.getType() == Application.ApplicationType.Android)
//            appType = "安卓";
//        else if (Gdx.app.getType() == Application.ApplicationType.WebGL)
//            appType = "网页";
//
//        manager = new AssetManager();
//
//
//        batch = new SpriteBatch();
//
//        texture = new Texture(Gdx.files.internal("data/animation.png"));
//
//        textureRegion = new TextureRegion(texture, 0, 0, iconWidth, iconHeight);
//        //textureRegion = new TextureRegion(texture, iconWidth, iconHeight, 0-iconWidth, -iconHeight);
//
//        sprite = new Sprite(textureRegion);
//        sprite.setSize(iconWidth * 2, iconHeight * 2);
//        sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);
//        //sprite.setOrigin(0, 0);
//        //sprite.setRotation(-30);
//        sprite.setPosition(padding + iconWidth + iconWidth * 2, padding);
//        //sprite.setColor(1, 0, 1, 1);
//
//        font = new BitmapFont(Gdx.files.internal("data/test_chinesefont.fnt"), Gdx.files.internal("data/test_chinesefont.png"), false);
//
//
//        fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("data/" + fontFile));
//        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
//        parameter.size = 40;
//        parameter.color = Color.BLACK;
//        parameter.characters += ttfFontTestStr;
//        ttfFont = fontGenerator.generateFont(parameter);
//        fontGenerator.dispose();
//
//    }
//
//    @Override
//    public void dispose() {
//        manager.dispose();
//        batch.dispose();
//        texture.dispose();
//    }
//
//    @Override
//    public void render() {
//        Gdx.gl.glClearColor(1, 1, 0, 1);
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//        batch.begin();
//        batch.draw(texture, padding, padding, iconWidth, iconHeight);
//        batch.draw(textureRegion, padding + iconWidth, padding, iconWidth * 2, iconHeight * 2);
//        sprite.draw(batch);
//        font.draw(batch, appType, padding, screenHeight);//single line
//        font.draw(batch, fontTestStr, padding, screenHeight - padding, 3 * padding, Align.left, true);//true for mutiline
//
//        ttfFont.draw(batch, ttfFontTestStr, padding, screenHeight - 2 * padding, screenWidth / 3, Align.left, true);//false -> not mutiline
//
//        batch.end();
//
//
//    }
//}