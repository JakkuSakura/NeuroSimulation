package com.jeekrs.neuro_simulation.system;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.collision.Ray;
import com.jeekrs.neuro_simulation.entities.Entity;
import com.jeekrs.neuro_simulation.entities.livings.Living;

import static com.jeekrs.neuro_simulation.GameScreen.systemManager;

public class Picker extends SimpleInputProcessor {

    public Entity selected;
    public Entity pickUp(Vector2 pos) {
        for (Entity e : systemManager.entitySystem.entities) {
            if (e instanceof Living) {
                if (((Living) e).contains(pos))
                    return e;
            }
        }
        return null;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        System.out.println("touched screen " + screenX + " " + screenY);
        Ray pickRay = systemManager.renderSystem.camera.getPickRay(screenX, screenY);
        Vector2 pos = new Vector2(pickRay.origin.x, pickRay.origin.y);
        System.out.println("touched stage" + pos);
        selected = pickUp(pos);
        System.out.println(selected);
        return selected != null;
    }

}
