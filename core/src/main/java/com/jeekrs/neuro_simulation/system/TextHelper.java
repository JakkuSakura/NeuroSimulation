package com.jeekrs.neuro_simulation.system;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class TextHelper {
    public static final FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("arial.ttf"));
    public static final BitmapFont font;

    static {
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 36;
        font = generator.generateFont(parameter);

    }

    static public void printf(Batch batch, float x, float y, String fmt, Object... args) {
        String str = String.format(fmt, args);
        font.draw(batch, str, x, y);
    }
}
