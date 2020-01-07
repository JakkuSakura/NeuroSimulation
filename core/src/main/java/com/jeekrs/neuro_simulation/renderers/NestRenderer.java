package com.jeekrs.neuro_simulation.renderers;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.jeekrs.neuro_simulation.components.data.Position;
import com.jeekrs.neuro_simulation.entities.AntNest;
import com.jeekrs.neuro_simulation.entities.Entity;

import static com.jeekrs.neuro_simulation.GameScreen.systemManager;

public class NestRenderer extends Renderer {
    private ShapeRenderer shapeRenderer = new ShapeRenderer();

    @Override
    public void render() {
        shapeRenderer.setProjectionMatrix(systemManager.renderSystem.camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        for (Entity entity : systemManager.entitySystem.populations.get(AntNest.class).getEntities()) {
            AntNest n = (AntNest) entity;
            Position pos = Position.getPosition(n);

            shapeRenderer.setColor(20, 20, 20, 1);
            shapeRenderer.rect(pos.x - 20, pos.y - 20,40, 40);

        }

        shapeRenderer.end();
    }
}
