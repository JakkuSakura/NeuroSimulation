package com.jeekrs.neuro_simulation.renderers;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.jeekrs.neuro_simulation.entities.Entity;
import com.jeekrs.neuro_simulation.entities.Wall;
import com.jeekrs.neuro_simulation.system.Renderer;

import static com.jeekrs.neuro_simulation.GameScreen.systemManager;

public class WallRenderer extends Renderer {

    private ShapeRenderer shapeRenderer = new ShapeRenderer();
    @Override
    public void render() {
        shapeRenderer.setProjectionMatrix(systemManager.renderSystem.camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        for (Entity e : systemManager.entitySystem.entities) {
            if (e instanceof Wall) {
                Wall lv = (Wall) e;
                Rectangle rectangle = lv.getRectangle();
                shapeRenderer.rect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);

            }
        }
        shapeRenderer.end();
    }
}
