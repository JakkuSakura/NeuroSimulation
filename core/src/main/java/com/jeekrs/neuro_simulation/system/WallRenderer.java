package com.jeekrs.neuro_simulation.system;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.jeekrs.neuro_simulation.entities.Wall;

import static com.jeekrs.neuro_simulation.GameScreen.systemManager;

public class WallRenderer extends Renderer {

    private ShapeRenderer shapeRenderer = new ShapeRenderer();
    @Override
    public void render() {
        shapeRenderer.setProjectionMatrix(systemManager.renderSystem.camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        systemManager.entitySystem.entities.forEach(e -> {
            if (e instanceof Wall) {
                Wall lv = (Wall) e;
                shapeRenderer.rect(lv.getPos().x, lv.getPos().y, lv.getWidth(), lv.getHeight());

            }
        });
        shapeRenderer.end();
    }
}
