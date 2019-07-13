package com.jeekrs.neuro_simulation.system;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.jeekrs.neuro_simulation.entities.Entity;
import com.jeekrs.neuro_simulation.entities.Living;

import static com.jeekrs.neuro_simulation.GameScreen.systemManager;

public class UISystem extends SimpleSystem {
    public Skin skin = new Skin();
    public Stage stage = new Stage();
    public SpriteBatch batch = new SpriteBatch();
    private Label label;


    @Override
    public void init() {
        systemManager.inputSystem.inputStack.stack.addFirst(systemManager.UISystem.stage);

        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        skin.add("white", new Texture(pixmap));
        skin.add("default", new BitmapFont());

        // Configure a TextButtonStyle and name it "default". Skin resources are stored by type, so this doesn't overwrite the font.
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
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

    @Override
    public void update(float delta) {
        StringBuilder describe = new StringBuilder();
        Entity lastSelect = systemManager.inputSystem.picker.selected;
        describe.delete(0, describe.length());
        if (systemManager.inputSystem.picker.selected != null) {
            if (lastSelect instanceof Living)
                describe.append(lastSelect);
            // etc
        } else {
            describe.append("Selected nothing");
        }

        label.setText(describe);

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
