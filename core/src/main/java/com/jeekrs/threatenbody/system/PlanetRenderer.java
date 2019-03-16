package com.jeekrs.threatenbody.system;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.jeekrs.threatenbody.entity.Planet;

public class PlanetRenderer extends Renderer {



    @Override
    public void render() {
        renderSystem.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        renderSystem.systemManager.worldSystem.entities.forEach(e -> {
            if (e instanceof Planet) {
                Planet pl = (Planet) e;
                renderSystem.shapeRenderer.circle((float) pl.pos.x, (float) pl.pos.y, (float) pl.radius);
            }
        });
        renderSystem.shapeRenderer.end();
    }

    @Override
    public void depose() {

    }
}
