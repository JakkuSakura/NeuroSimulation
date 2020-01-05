package com.jeekrs.neuro_simulation.systems;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class InfoStage {
    private Stage stage;
    private Skin skin;
    private Label resourceLabel;
    private Label baseCampLabel;
    private Label nestLabel;

    public InfoStage(Skin s) {
        skin = s;
        stage = new Stage();
        resourceLabel = new Label("Resource:", skin);
        baseCampLabel = new Label("Camp: ", skin);
        nestLabel = new Label("Nest: ", skin);
    }

    public void update() {

    }

    public Stage getStage() {
        return stage;
    }
}
