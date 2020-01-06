package com.jeekrs.neuro_simulation.systems;

import com.jeekrs.neuro_simulation.entities.Ant;
import com.jeekrs.neuro_simulation.utils.RandomUtil;

import static com.jeekrs.neuro_simulation.GameScreen.systemManager;

public class SwarmSystem extends SimpleSystem {
    private long last = System.currentTimeMillis();

    @Override
    public void update(float delta) {
        long current = System.currentTimeMillis();
        if (current - last >= 500) {
            last = current;

            for (int i = 0; i < 5; i++) {
                Ant e = new Ant(RandomUtil.nextFloat(-1000, 1000), RandomUtil.nextFloat(-1000, 1000));
                systemManager.entitySystem.addEntity(e);
            }

        }
    }
}
