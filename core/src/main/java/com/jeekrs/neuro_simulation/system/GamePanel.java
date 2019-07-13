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

import static com.jeekrs.neuro_simulation.GameScreen.systemManager;

public class GamePanel extends UIComponent {
    public Stage stage = new Stage();
    private Table table = new Table();
    private Skin skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
    private Label left = new Label("GamePanel", skin);
    private Label middle = new Label("Information", skin);
    private Label right = new Label("Information", skin);
//    private ImageButton

    public Drawable getBackgroundColor(Color color) {
        Pixmap bgPixmap = new Pixmap(1, 1, Pixmap.Format.RGB565);
        bgPixmap.setColor(color);
        bgPixmap.fill();
        return new TextureRegionDrawable(new TextureRegion(new Texture(bgPixmap)));

    }

    private int height = 200;
    @Override
    void init() {
        stage = new Stage();
//        stage.setDebugAll(true);


        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        table = new Table();

        stage.addActor(table);

        table.setPosition(0, 0);

        table.setSize(Gdx.graphics.getWidth(), height);
//        table.setFillParent(true);
        table.setBackground(getBackgroundColor(Color.OLIVE));


        left.getStyle().font = TextHelper.font;
        middle.getStyle().font = TextHelper.font;
        right.getStyle().font = TextHelper.font;

        left.setScale(2);
        middle.setScale(2);
        right.setScale(2);

        table.add(left).padRight(20);
        table.add(middle).padRight(20);
        table.add(right);
        // Table的坐标是默认从屏幕左下角开始的， 调试中的蓝框就是Table
        // 如果设置下面属性那么居中显示


        systemManager.inputSystem.inputStack.stack.addFirst(stage);

    }

    @Override
    void update() {
        {
            StringBuilder sb = new StringBuilder();
            sb.append(String.format("You are %s\n", systemManager.agendaSystem.agendas.get(systemManager.agendaSystem.playerAgenda)));

            sb.append(String.format("You have %.2f units of food\n", systemManager.resourceSystem.getFood(systemManager.agendaSystem.playerAgenda)));
            middle.setText(sb);
        }

        {
            StringBuilder sb = new StringBuilder();
            Entity selected = systemManager.inputSystem.picker.selected;
            if (selected != null) {
                sb.append("You chose ").append(selected.toString()).append("\n");
            }
            right.setText(sb);
        }

//        if (selected instanceof BaseCamp && ((BaseCamp) selected).getAgenda() == systemManager.agendaSystem.playerAgenda)
//        {
//
//        }
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
