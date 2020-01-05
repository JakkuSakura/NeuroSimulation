package com.jeekrs.neuro_simulation.systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.util.ArrayList;

import static com.jeekrs.neuro_simulation.GameScreen.systemManager;

public class SelectSpeciesComponent extends UIComponent {

    public TextButton selected = null;
    public Stage stage = new Stage();
    private Table table = new Table();
    private Skin skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
    private ArrayList<TextButton> selectButtons = new ArrayList<TextButton>() {{
        TextButton.TextButtonStyle textButtonStyle = skin.get("default", TextButton.TextButtonStyle.class);
        String[] names = {"Ants", "Bees"};
        for (int i = 0; i < names.length; ++i) {
            TextButton selectButton = new TextButton(names[i], textButtonStyle);
            add(selectButton);
        }
    }};


    public Drawable getBackgroundColor(Color color) {
        Pixmap bgPixmap = new Pixmap(1, 1, Pixmap.Format.RGB565);
        bgPixmap.setColor(color);
        bgPixmap.fill();
        return new TextureRegionDrawable(new TextureRegion(new Texture(bgPixmap)));

    }

    @Override
    void init() {
        stage = new Stage();
//        stage.setDebugAll(true);


        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        table = new Table();
        stage.addActor(table);
        table.setFillParent(true);
//        table.setBackground(getBackgroundColor(Color.OLIVE));


        Label label = new Label("Select your species", skin);
        label.getStyle().font = TextHelper.font;
//        label.setFontScale(2);
        table.add(label).padBottom(10);
        // Table的坐标是默认从屏幕左下角开始的， 调试中的蓝框就是Table
        // 如果设置下面属性那么居中显示

        for (TextButton selectButton : selectButtons) {
            table.row();
            table.add(selectButton).width(150);
        }

        systemManager.inputSystem.inputStack.stack.addFirst(stage);

    }

    @Override
    void update() {
        stage.act();
        stage.draw();
        for (TextButton e : selectButtons) {
            if (e.isPressed()) {
                System.out.println(e.getText());
                systemManager.UISystem.removeUIComponent(this);
                selected = e;
                systemManager.UISystem.addUIComponent(new GamePanel());

            }
        }

    }

    @Override
    void dispose() {
        skin.dispose();
        stage.dispose();
        systemManager.inputSystem.inputStack.stack.removeFirst();
    }

    @Override
    void resize(int w, int h) {
        stage.getViewport().update(w, h, true);
    }

}
