package com.jeekrs.neuro_simulation.system;

import com.badlogic.gdx.math.Intersector;
import com.jeekrs.neuro_simulation.component.Movable;
import com.jeekrs.neuro_simulation.entities.Entity;
import com.jeekrs.neuro_simulation.entities.Living;
import com.jeekrs.neuro_simulation.entities.Wall;

import java.util.ArrayList;


@SuppressWarnings("ALL")
public class PhysicsSystem extends SimpleSystem {
    public final static float G = 10;


    public void update(float delta) {
        for (Entity e1 : systemManager.worldSystem.entities) {
            if (e1 instanceof Movable) {

                e1.getVel().mulAdd(e1.getAcc(), delta);

                e1.getVel().x *= 1 - 0.5f * delta;
                e1.getVel().y *= 1 - 0.5f * delta;

                e1.getPos().mulAdd(e1.getVel(), delta);

                // wind effect
//                e1.getPos().mulAdd(Vector2.X, delta * 80);
            }
        }

        ArrayList<Entity> entities = new ArrayList<>();
        for (Entity e1 : systemManager.worldSystem.entities) {
            if (e1 instanceof Living) {
                for (Entity e2 : systemManager.worldSystem.entities) {
                    if (e2 instanceof Wall) {
                        if (Intersector.overlaps(((Living) e1).getCircle(), ((Wall) e2).getRectangle()))
                            entities.add(e1);
                    }
                }
            }
        }
        entities.forEach(systemManager.worldSystem.entities::remove);
    }

}

