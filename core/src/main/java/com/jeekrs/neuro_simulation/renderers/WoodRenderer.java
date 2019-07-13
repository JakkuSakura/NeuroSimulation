package com.jeekrs.neuro_simulation.renderers;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.jeekrs.neuro_simulation.entities.Entity;
import com.jeekrs.neuro_simulation.entities.functionals.Wood;
import com.jeekrs.neuro_simulation.system.Renderer;

import static com.jeekrs.neuro_simulation.GameScreen.systemManager;

public class WoodRenderer extends Renderer {
    private ShapeRenderer shapeRenderer = new ShapeRenderer();

    @Override
    public void render() {
        shapeRenderer.setProjectionMatrix(systemManager.renderSystem.camera.combined);
        shapeRenderer.setColor(.1f, .9f, 0, 1);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        for (Entity entity : systemManager.entitySystem.entities) {
            if (entity instanceof Wood) {
                Wood n = (Wood) entity;
                Rectangle rect = n.getRectangle();
                shapeRenderer.rect(rect.x, rect.y, rect.width, rect.height);

            }
        }
        shapeRenderer.end();
    }
}
