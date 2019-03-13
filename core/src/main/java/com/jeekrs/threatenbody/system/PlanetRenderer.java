package com.jeekrs.threatenbody.system;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jeekrs.threatenbody.entity.Planet;

public class PlanetRenderer extends Renderer {

    public Texture sphere = new Texture("box.png");

    @Override
    public void render() {
        world.entities.forEach(e -> {
            if (e instanceof Planet) {
                Planet pl = (Planet) e;
                batch.draw(sphere, (float) pl.physicsComponent.p.x, (float) pl.physicsComponent.p.y);
            }
        });
    }

    @Override
    public void depose() {

    }
}
