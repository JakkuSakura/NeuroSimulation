package com.jeekrs.threatenbody.system; /*******************************************************************************
 * Copyright 2011 See AUTHORS file.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.jeekrs.threatenbody.entity.Entity;
import com.jeekrs.threatenbody.entity.Planet;

public class UISystem extends SimpleSystem {
    public Skin skin = new Skin();
    public Stage stage = new Stage();
    public SpriteBatch batch = new SpriteBatch();
    private Label label;


    @Override
    public void init() {
        systemManager.inputSystem.inputStack.stack.addFirst(systemManager.UISystem.stage);

        Pixmap pixmap = new Pixmap(1, 1, Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        skin.add("white", new Texture(pixmap));
        skin.add("default", new BitmapFont());

        // Configure a TextButtonStyle and name it "default". Skin resources are stored by type, so this doesn't overwrite the font.
        TextButtonStyle textButtonStyle = new TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("white", Color.DARK_GRAY);
        textButtonStyle.down = skin.newDrawable("white", Color.DARK_GRAY);
        textButtonStyle.checked = skin.newDrawable("white", Color.BLUE);
        textButtonStyle.over = skin.newDrawable("white", Color.LIGHT_GRAY);
        textButtonStyle.font = skin.getFont("default");
        skin.add("default", textButtonStyle);

        // Configure a LabelStyle and name it "default". Skin resources are stored by type, so this doesn't overwrite the font.
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.fontColor = Color.WHITE;
        labelStyle.font = skin.getFont("default");
        skin.add("default", labelStyle);

        // Create a table that fills the screen. Everything else will go inside this table.
        Table table = new Table();
        table.setSize(Gdx.graphics.getWidth(), 128);

        stage.addActor(table);

        table.setBackground(skin.newDrawable("white", Color.DARK_GRAY));


        label = new Label("Uninitialized", labelStyle);
        table.add(label).pad(10);


    }

    public Entity lastSelect;

    @Override
    public void update(float delta) {
        if (lastSelect != systemManager.inputSystem.selected) {
            StringBuilder describe = new StringBuilder();
            lastSelect = systemManager.inputSystem.selected;
            describe.delete(0, describe.length());
            if (systemManager.inputSystem.selected != null) {
                if (lastSelect instanceof Planet)
                    describe.append(lastSelect);
                // etc
            } else {
                describe.append("Selected nothing");
            }
            label.setText(describe);
        }

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }


    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }
}
