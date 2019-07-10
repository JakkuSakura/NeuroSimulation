package com.jeekrs.neuro_simulation.system;

import com.jeekrs.neuro_simulation.entities.Entity;
import com.jeekrs.neuro_simulation.entities.Living;
import com.jeekrs.neuro_simulation.processors.NeuroProcessor;
import com.jeekrs.neuro_simulation.utils.RandomUtil;

import java.util.ArrayList;
import java.util.TreeSet;

public class EntitySystem extends SimpleSystem {
    private long last_time = System.currentTimeMillis();

    public void update(float delta) {
        systemManager.worldSystem.entities.forEach(entity -> {
            if (entity instanceof Living) {
                Living living = (Living) entity;
                living.effect(living.process(living.detect()));
            }
        });
        if (System.currentTimeMillis() - last_time > 1000 * 2) {
            last_time = System.currentTimeMillis();
            TreeSet<Entity> entities = (TreeSet<Entity>) systemManager.worldSystem.entities.clone();
            for (Entity e : entities) {
                if (e instanceof Living && systemManager.worldSystem.entities.size() < 800) {
                    Living living = (Living) e.clone();
                    living.getPos().x += RandomUtil.nextFloat(-50, 50);
                    living.getPos().y += RandomUtil.nextFloat(-50, 50);
                    if (living.getProcessor() instanceof NeuroProcessor) {
                        ((NeuroProcessor) living.getProcessor()).adjust(0.2f);
                    }
                    systemManager.worldSystem.entities.add(living);
                }
            }
        }

        // crowded
        ArrayList<Living> dead = new ArrayList<>();
        for (Entity entity : systemManager.worldSystem.entities) {
            if (entity instanceof Living) {
                Living l1 = (Living) entity;
                int count = 0;
                for (Entity e2 : systemManager.worldSystem.entities) {
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
        dead.forEach(systemManager.worldSystem.entities::remove);
    }

}
