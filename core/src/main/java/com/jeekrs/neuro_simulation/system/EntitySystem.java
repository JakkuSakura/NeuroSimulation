package com.jeekrs.neuro_simulation.system;

import com.jeekrs.neuro_simulation.entities.Living;

public class EntitySystem extends SimpleSystem {
    public void update(float delta) {
        systemManager.worldSystem.entities.forEach(entity -> {
            if (entity instanceof Living)
            {
                Living living = (Living) entity;
                living.effect(living.process(living.collect()));
            }
        });
    }

}
