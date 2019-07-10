package com.jeekrs.neuro_simulation.system;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.jeekrs.neuro_simulation.entities.Living;

public class LivingRenderer extends Renderer {

    @Override
    public void render() {
        renderSystem.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        renderSystem.batch.begin();
        renderSystem.systemManager.worldSystem.entities.forEach(e -> {
            if (e instanceof Living) {
                Living lv = (Living) e;
                renderSystem.shapeRenderer.circle((float) lv.getPhy().pos.x, (float) lv.getPhy().pos.y, (float) lv.getRadius());
                TextHelper.printf(renderSystem.batch, lv.getPhy().pos.x - 30, lv.getPhy().pos.y + 55, "%s", lv.toString());
            }
        });
        renderSystem.batch.end();
        renderSystem.shapeRenderer.end();
    }

    @Override
    public void depose() {

    }
}
