package com.jeekrs.neuro_simulation.system;

import com.jeekrs.neuro_simulation.component.Shape;
import com.jeekrs.neuro_simulation.entities.Entity;
import com.jeekrs.neuro_simulation.component.Movable;
import com.jeekrs.neuro_simulation.entities.Living;
import com.jeekrs.neuro_simulation.entities.Wall;
import com.jeekrs.neuro_simulation.utils.Geometry;

import java.util.ArrayList;


@SuppressWarnings("ALL")
public class PhysicsSystem extends SimpleSystem {
    public final static double G = 10;


    public void update(float delta) {
        for (Entity e1 : systemManager.worldSystem.entities) {
            if (e1 instanceof Movable) {

                e1.getPhy().vel.add(e1.getPhy().acc.multi(delta));

                e1.getPhy().vel.multiBy(1 - 0.5f*delta);

                e1.getPhy().pos.add(e1.getPhy().vel.multi(delta));
            }
        }

        ArrayList<Entity> entities = new ArrayList<>();
        for (Entity e1 : systemManager.worldSystem.entities) {
            if (e1 instanceof Living) {
                for (Entity e2 : systemManager.worldSystem.entities) {
                    if (e2 instanceof Wall) {
                        if (Geometry.intersects((Shape)e1, (Shape)e2))
                            entities.add(e1);
                    }
                }
            }
        }
        entities.forEach(systemManager.worldSystem.entities::remove);
    }

}

