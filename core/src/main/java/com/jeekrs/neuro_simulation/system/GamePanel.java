package com.jeekrs.neuro_simulation.system;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.jeekrs.neuro_simulation.entities.Entity;
import com.jeekrs.neuro_simulation.entities.livings.Living;

import static com.jeekrs.neuro_simulation.GameScreen.systemManager;

public class GamePanel extends UIComponent {
    public Stage stage = new Stage();
    private Table table = new Table();
    private Skin skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
    private Label information = new Label("Information", skin);


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

        table.setPosition(Gdx.graphics.getWidth() - 100, 0);

        table.setSize(100, Gdx.graphics.getHeight());
//        table.setFillParent(true);
        table.setBackground(getBackgroundColor(Color.OLIVE));


        Label label = new Label("GamePanel", skin);
        label.getStyle().font = TextHelper.font;
        table.add(label);
        table.row();
        table.add(information);
        // Table的坐标是默认从屏幕左下角开始的， 调试中的蓝框就是Table
        // 如果设置下面属性那么居中显示


        systemManager.inputSystem.inputStack.stack.addFirst(stage);

    }

    @Override
    void update() {
        Entity selected = systemManager.inputSystem.picker.selected;
        if (selected instanceof Living) {
            information.setText(selected.toString());
        }
        stage.act();
        stage.draw();

    }

    @Override
    void resize(int w, int h) {
        stage.getViewport().update(w, h, true);
    }

    @Override
    void dispose() {
        skin.dispose();
        stage.dispose();

        systemManager.inputSystem.inputStack.stack.removeFirst();
    }
}
