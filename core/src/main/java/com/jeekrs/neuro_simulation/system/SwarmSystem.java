package com.jeekrs.neuro_simulation.system;

import com.jeekrs.neuro_simulation.entities.Ant;
import com.jeekrs.neuro_simulation.utils.RandomUtil;

import static com.jeekrs.neuro_simulation.GameScreen.systemManager;

public class SwarmSystem extends SimpleSystem {
    private long last = System.currentTimeMillis();

    @Override
    public void update(float delta) {
        long current = System.currentTimeMillis();
        if (current - last >= 100) {
            last = current;

            Ant e = new Ant(RandomUtil.nextFloat(-300, 300), RandomUtil.nextFloat(-300, 300));
            e.randomLink(RandomUtil.nextInt(15, 60));
            systemManager.entitySystem.addEntity(e);

        }
    }
}
