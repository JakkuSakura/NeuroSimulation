package com.jeekrs.neuro_simulation.renderers;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.jeekrs.neuro_simulation.components.data.Position;
import com.jeekrs.neuro_simulation.entities.Entity;
import com.jeekrs.neuro_simulation.entities.Food;

import static com.jeekrs.neuro_simulation.GameScreen.systemManager;

public class FoodRenderer extends Renderer {
    private ShapeRenderer shapeRenderer = new ShapeRenderer();

    @Override
    public void render() {
        shapeRenderer.setProjectionMatrix(systemManager.renderSystem.camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        for (Entity entity : systemManager.entitySystem.entities) {
            if (entity instanceof Food) {
                Food n = (Food) entity;
                Position pos = Position.getPosition(n);

                shapeRenderer.setColor(n.getFood().energy * 10 / 255, n.getFood().health * 10 / 255, n.getFood().type, 1);
                shapeRenderer.circle(pos.x, pos.y, 5);
            }
        }

        shapeRenderer.end();
    }
}
