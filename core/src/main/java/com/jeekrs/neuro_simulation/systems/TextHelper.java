package com.jeekrs.neuro_simulation.systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class TextHelper {
    public static final FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("arial.ttf"));
    public static final BitmapFont font = createFont(generator, 256);

    public static void setLineHeight(BitmapFont font, float height) {
        font.getData().setScale(height * font.getScaleY() / font.getLineHeight());
    }

    static public BitmapFont createFont(FreeTypeFontGenerator ftfg, float dp) {
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = (int) (dp * Gdx.graphics.getDensity());
        return ftfg.generateFont(parameter);
    }

    static public void printf(Batch batch, float x, float y, String fmt, Object... args) {
        String str = String.format(fmt, args);
        font.draw(batch, str, x, y);
    }
}
