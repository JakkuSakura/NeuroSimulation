package com.jeekrs.neuro_simulation.system;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Timer;
import com.jeekrs.neuro_simulation.entities.Entity;
import com.jeekrs.neuro_simulation.entities.livings.Living;
import com.jeekrs.neuro_simulation.entities.nest.Nest;

import java.util.ArrayList;

import static com.jeekrs.neuro_simulation.GameScreen.systemManager;

public class UISystem extends SimpleSystem {
    private Stage stage = new Stage();
    private SpriteBatch batch = new SpriteBatch();
    private TextureAtlas textureAtlas;
    private int foodCnt = 0;
    private int popCnt;
    private int campCnt = 1;
    private int nestCnt = 1;
    private int enemiesKilled = 0;
    private ArrayList<Camp> campArrayList = new ArrayList<Camp>();
    private ArrayList<Nest> nestArrayList = new ArrayList<Nest>();
    Dialog dialog;
    Dialog constructionDialog;

    Skin skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
    ArrayList<TextButton> selectButtons = new ArrayList<TextButton>(){{
        TextButton.TextButtonStyle textButtonStyle = skin.get("default", TextButton.TextButtonStyle.class);
        String[] names = {"Ants", "Bees"};
        for (int i = 0; i < names.length; ++i) {
            TextButton selectButton = new TextButton(names[i], textButtonStyle);

            selectButton.getLabelCell().padLeft(10f).padRight(10f);

            selectButton.sizeBy(200, 100);
            add(selectButton);
        }
    }};

    @Override
    public void init() {

        // Configure a LabelStyle and name it "default". Skin resources are stored by type, so this doesn't overwrite the font.
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.fontColor = Color.WHITE;
        labelStyle.font = skin.getFont("font-button");

        // create dialog window to pop up in the beginning and then disappears once the client click
        dialog = new Dialog("Select a species", skin);
        dialog.show(stage);

        // add actors to show on stage
        selectButtons.forEach(dialog::add);
        dialog.align(0);

        // create a dialog window for choosing construction
        constructionDialog = new Dialog("Construct", skin);
        constructionDialog.setPosition(Gdx.graphics.getWidth()-1, Gdx.graphics.getHeight()-1);
        constructionDialog.show(stage);

        systemManager.inputSystem.inputStack.stack.addFirst(systemManager.UISystem.stage);
        systemManager.inputSystem.inputStack.stack.addFirst(systemManager.UISystem.dialog.getStage());

    }

    @Override
    public void update(float delta) {

        selectButtons.forEach(e -> {
            if(e.isPressed())
                dialog.hide();
        });

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
        // skin.dispose();
    }

}
