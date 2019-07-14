package com.jeekrs.neuro_simulation.system;

import com.badlogic.gdx.math.Intersector;
import com.jeekrs.neuro_simulation.entities.Entity;
import com.jeekrs.neuro_simulation.entities.Wall;
import com.jeekrs.neuro_simulation.entities.livings.Living;
import com.jeekrs.neuro_simulation.entities.livings.NeuralLiving;
import com.jeekrs.neuro_simulation.interfaces.Alive;
import com.jeekrs.neuro_simulation.interfaces.Movable;
import com.jeekrs.neuro_simulation.utils.RandomUtil;

import java.util.ArrayList;
import java.util.TreeSet;

public class EntitySystem extends SimpleSystem {
    public ArrayList<Entity> dead = new ArrayList<>();
    public ArrayList<Entity> newborn = new ArrayList<>();

    public TreeSet<Entity> entities = new TreeSet<>();

    public void update(float delta) {
        livingEffect();

        movement(delta);

        collision();
        deadForCrowded(delta, dead);
        health();


        entities.removeAll(dead);
        entities.addAll(newborn);
        dead.clear();
        newborn.clear();
    }

    private void health() {
        entities.forEach(e -> {
            if (e instanceof Alive) {
                if (((Alive) e).getHealth() <= 0)
                    dead.add(e);
            }
        });
    }

    private void deadForCrowded(float delta, ArrayList<Entity> dead) {
        // crowded

        for (Entity entity : entities) {
            if (entity instanceof Living) {
                Living l1 = (Living) entity;
                int count = 0;
                for (Entity e2 : entities) {
                    if (e2 instanceof Living) {
                        Living l2 = (Living) e2;
                        if (l1.getPos().dst(l2.getPos()) < 50) {
                            count += 1;
                        }
                    }
                }
                if ((count - 5) * delta * 10 > RandomUtil.nextFloat())
                    l1.setHealth(l1.getHealth() - 30);
            }
        }
    }

    private void collision() {
        for (Entity e1 : entities) {
            if (e1 instanceof Living) {
                for (Entity e2 : entities) {
                    if (e2 instanceof Wall) {
                        if (Intersector.overlaps(((Living) e1).getCircle(), ((Wall) e2).getRectangle()))
                            dead.add(e1);
                    }
                }
            }
        }
    }

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

    private void livingEffect() {
        entities.forEach(entity -> {
            if (entity instanceof NeuralLiving) {
                NeuralLiving living = (NeuralLiving) entity;
                living.effect(living.process(living.detect()));
            }
        });
    }

    public boolean addEntity(Entity p1) {
        return newborn.add(p1);
    }
}
