package com.jeekrs.neuro_simulation.system;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.jeekrs.neuro_simulation.entities.Wall;

public class WallRenderer extends Renderer {
    @Override
    public void render() {
        renderSystem.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        renderSystem.systemManager.worldSystem.entities.forEach(e -> {
            if (e instanceof Wall) {
                Wall lv = (Wall) e;
                renderSystem.shapeRenderer.rect(lv.getPos().x, lv.getPos().y, lv.getWidth(), lv.getHeight());

            }
        });
        renderSystem.shapeRenderer.end();
    }
}