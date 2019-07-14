package com.jeekrs.neuro_simulation.renderers;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.jeekrs.neuro_simulation.entities.Entity;
import com.jeekrs.neuro_simulation.entities.nest.Nest;
import com.jeekrs.neuro_simulation.system.Renderer;

import static com.jeekrs.neuro_simulation.GameScreen.systemManager;

public class NestRenderer extends Renderer {
    private ShapeRenderer shapeRenderer = new ShapeRenderer();

    @Override
    public void render() {
        shapeRenderer.setProjectionMatrix(systemManager.renderSystem.camera.combined);
        shapeRenderer.setColor(1, 0, 0, 1);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        for (Entity entity : systemManager.entitySystem.entities) {
            if (entity instanceof Nest) {
                Nest n = (Nest) entity;
                renderNest(n);
            }
        }
        shapeRenderer.end();
    }

    public void renderNest(Nest n) {
        Rectangle rect = n.getRectangle();
        shapeRenderer.rect(rect.x, rect.y, rect.width, rect.height);
    }
}
