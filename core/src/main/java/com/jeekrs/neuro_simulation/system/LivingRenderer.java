package com.jeekrs.neuro_simulation.system;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.jeekrs.neuro_simulation.entities.Entity;
import com.jeekrs.neuro_simulation.entities.Living;

import static com.jeekrs.neuro_simulation.GameScreen.systemManager;

public class LivingRenderer extends Renderer {

    @Override
    public void render() {
        int count = 0;
        renderSystem.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        renderSystem.batch.begin();
        for (Entity e : systemManager.entitySystem.entities) {
            if (e instanceof Living) {
                Living lv = (Living) e;
                renderSystem.shapeRenderer.circle(lv.getPos().x, lv.getPos().y, lv.getRadius());
                TextHelper.printf(renderSystem.batch, lv.getPos().x - 30, lv.getPos().y + 55, "%s", lv.toString());
                count += 1;
                if (count % 20 == 0) {
                    renderSystem.batch.end();
                    renderSystem.shapeRenderer.end();
                    renderSystem.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
                    renderSystem.batch.begin();
                }
            }
        }
        renderSystem.batch.end();
        renderSystem.shapeRenderer.end();
    }

    @Override
    public void depose() {

    }
}
