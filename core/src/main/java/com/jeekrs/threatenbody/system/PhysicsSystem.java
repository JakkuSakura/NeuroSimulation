package com.jeekrs.threatenbody.system;

import com.jeekrs.threatenbody.entity.Entity;
import com.jeekrs.threatenbody.entity.Planet;
import com.jeekrs.threatenbody.interfaces.Collidable;
import com.jeekrs.threatenbody.interfaces.Gravity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("ALL")
public class PhysicsSystem extends SimpleSystem {
    public final static double G = 10;


    public void update(float delta) {
        ArrayList<Gravity> entities = new ArrayList<>();
        for (Entity e1 : systemManager.world.entities) {
            if (e1 instanceof Gravity) {
                entities.add((Gravity) e1);
            }
        }

        Set<Entity> toAdd = new HashSet<>();
        Set<Entity> toDel = new HashSet<>();
        for (Gravity gr1 : entities) {
            double acc_x = 0;
            double acc_y = 0;

            for (Gravity gr2 : entities) {
                if (gr1 == gr2) continue;

                if (toDel.contains(gr1) || toDel.contains(gr2))
                    continue;

                double dx = gr2.getPos().x - gr1.getPos().x;
                double dy = gr2.getPos().y - gr1.getPos().y;
                double dist = gr2.getPos().dist(gr1.getPos());
                if (gr1 instanceof Collidable && gr2 instanceof Collidable) {
                    if (dist < ((Collidable) gr1).getRadius() + ((Collidable) gr2).getRadius()) {
                        collide(toAdd, toDel, gr1, gr2);
                    }

                }
                double totacc = G * gr2.getMass() / (dist * dist);
                acc_x += totacc * dx / dist;
                acc_y += totacc * dy / dist;
            }

            gr1.getAcc().set(acc_x, acc_y);

            entities.forEach(ent ->
            {
                ((Gravity) ent).getVel().add(((Gravity) ent).getAcc().multi(delta));
                ((Gravity) ent).getPos().add(((Gravity) ent).getVel().multi(delta));
            });

            systemManager.world.entities.addAll(toAdd);
            systemManager.world.entities.removeAll(toDel);
        }

    }

    private void collide(Set<Entity> toAdd, Set<Entity> toDel, Entity lhs, Entity rhs) {
        if (lhs instanceof Planet && rhs instanceof Planet) {
            Planet gr1 = (Planet) lhs;
            Planet gr2 = (Planet) rhs;
            toDel.add(gr1);
            toDel.add(gr2);

            double totMass = gr1.getMass() + gr2.getMass();

            double vx = (gr1.getVel().x * gr1.getMass() + gr2.getVel().x * gr2.getMass()) / totMass;
            double vy = (gr1.getVel().y * gr1.getMass() + gr2.getVel().y * gr2.getMass()) / totMass;

            double x = (gr1.getPos().x * gr1.getMass() + gr2.getPos().x * gr2.getMass()) / totMass;
            double y = (gr1.getPos().y * gr1.getMass() + gr2.getPos().y * gr2.getMass()) / totMass;
            Planet planet = new Planet(Math.sqrt(gr1.getRadius() * gr1.getRadius() + gr2.getRadius() * gr2.getRadius()), totMass);
            planet.vel.set(vx, vy);
            planet.pos.set(x, y);

            toAdd.add(planet);

        }
    }
}

