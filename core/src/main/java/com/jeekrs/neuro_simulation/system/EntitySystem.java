package com.jeekrs.neuro_simulation.system;

import com.jeekrs.neuro_simulation.entities.Entity;
import com.jeekrs.neuro_simulation.interfaces.Movable;

import java.util.ArrayList;
import java.util.TreeSet;

public class EntitySystem extends SimpleSystem {
    public ArrayList<Entity> dead = new ArrayList<>();
    public ArrayList<Entity> newborn = new ArrayList<>();

    public TreeSet<Entity> entities = new TreeSet<>();

    public void update(float delta) {

        movement(delta);


//        deadForCrowded(delta, dead);
//        health();


        entities.removeAll(dead);
        entities.addAll(newborn);
        dead.clear();
        newborn.clear();
    }

//    private void health() {
//        entities.forEach(e -> {
//            if (e instanceof Alive) {
//                if (((Alive) e).getHealth() <= 0)
//                    dead.add(e);
//            }
//        });
//    }

    private void movement(float delta) {
        for (Entity e1 : entities) {
            if (e1 instanceof Movable) {
                Movable m = (Movable) e1;
                m.getVel().x *= 1 - 0.5f * delta;
                m.getVel().y *= 1 - 0.5f * delta;
                m.getPos().mulAdd(m.getVel(), delta);
            }
        }
    }

    private void livingUpdate() {
        entities.forEach(entity -> {
//            entity.update();
        });
    }

    public boolean addEntity(Entity p1) {
        return newborn.add(p1);
    }
}
