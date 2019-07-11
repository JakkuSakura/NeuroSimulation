package com.jeekrs.neuro_simulation.system;

import com.badlogic.gdx.math.Intersector;
import com.jeekrs.neuro_simulation.component.Movable;
import com.jeekrs.neuro_simulation.entities.Entity;
import com.jeekrs.neuro_simulation.entities.Living;
import com.jeekrs.neuro_simulation.entities.Wall;
import com.jeekrs.neuro_simulation.processors.NeuroProcessor;
import com.jeekrs.neuro_simulation.utils.RandomUtil;

import java.util.ArrayList;
import java.util.TreeSet;

public class EntitySystem extends SimpleSystem {
    private long last_time = System.currentTimeMillis();
    public ArrayList<Entity> dead = new ArrayList<>();
    public ArrayList<Entity> newborn = new ArrayList<>();

    public TreeSet<Entity> entities = new TreeSet<>();

    public void update(float delta) {
        dead.clear();
        newborn.clear();
        livingEffect();

        movement(delta);

        collision();
        breed();
        deadForCrowded(delta, dead);


        entities.removeAll(dead);
        entities.addAll(newborn);
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
                    dead.add(l1);
            }
        }
    }

    private void breed() {
        if (System.currentTimeMillis() - last_time > 1000 * 2) {
            last_time = System.currentTimeMillis();

            for (Entity e : entities) {
                if (e instanceof Living && entities.size() < 800) {
                    Living living = (Living) e.clone();
                    living.getPos().x += RandomUtil.nextFloat(-50, 50);
                    living.getPos().y += RandomUtil.nextFloat(-50, 50);
                    if (living.getProcessor() instanceof NeuroProcessor) {
                        ((NeuroProcessor) living.getProcessor()).adjust(0.2f);
                    }
                    newborn.add(living);
                }
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
                Movable m = (Movable)e1;
                m.getVel().x *= 1 - 0.5f * delta;
                m.getVel().y *= 1 - 0.5f * delta;
                m.getPos().mulAdd(m.getVel(), delta);
            }
        }
    }

    private void livingEffect() {
        entities.forEach(entity -> {
            if (entity instanceof Living) {
                Living living = (Living) entity;
                living.effect(living.process(living.detect()));
            }
        });
    }

    public boolean addEntity(Entity p1) {
        return entities.add(p1);
    }
}
