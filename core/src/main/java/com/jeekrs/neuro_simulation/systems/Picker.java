package com.jeekrs.neuro_simulation.systems;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.collision.Ray;
import com.jeekrs.neuro_simulation.components.abilities.Hittable;
import com.jeekrs.neuro_simulation.entities.Entity;
import com.jeekrs.neuro_simulation.populations.Population;

import static com.jeekrs.neuro_simulation.GameScreen.systemManager;

public class Picker extends SimpleInputProcessor {

    public Entity selected;

    public Entity pickUp(Vector2 pos) {
        for (Population pop : systemManager.entitySystem.populations.values()) {
            for (Entity e : pop.getEntities()) {
                // not "pickable" until now
                Hittable c = e.getComponentByClass(Hittable.class);
                if (c != null && c.contains(pos.x, pos.y)) return e;
            }
        }
        return null;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        System.out.println("touched window " + screenX + " " + screenY);
        Ray pickRay = systemManager.renderSystem.camera.getPickRay(screenX, screenY);
        Vector2 pos = new Vector2(pickRay.origin.x, pickRay.origin.y);
        System.out.println("touched world" + pos);
        selected = pickUp(pos);
        System.out.println(selected);
        return selected != null;
    }

}
