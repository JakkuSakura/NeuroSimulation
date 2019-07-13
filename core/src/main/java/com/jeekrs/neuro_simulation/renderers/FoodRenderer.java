package com.jeekrs.neuro_simulation.renderers;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.jeekrs.neuro_simulation.entities.Entity;
import com.jeekrs.neuro_simulation.entities.functionals.Food;
import com.jeekrs.neuro_simulation.system.Renderer;

import static com.jeekrs.neuro_simulation.GameScreen.systemManager;

public class FoodRenderer extends Renderer {
    private ShapeRenderer shapeRenderer = new ShapeRenderer();

    @Override
    public void render() {
        shapeRenderer.setProjectionMatrix(systemManager.renderSystem.camera.combined);
        shapeRenderer.setColor(1, 1, 0, 1);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        for (Entity entity : systemManager.entitySystem.entities) {
            if (entity instanceof Food) {
                Food n = (Food) entity;
                Circle cir = n.getCircle();
                shapeRenderer.circle(cir.x, cir.y, cir.radius);
            }
        }

        shapeRenderer.end();
    }
}
