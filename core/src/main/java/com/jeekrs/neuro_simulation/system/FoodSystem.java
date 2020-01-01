package com.jeekrs.neuro_simulation.system;

import com.jeekrs.neuro_simulation.entities.Food;

import com.jeekrs.neuro_simulation.utils.RandomUtil;

import static com.jeekrs.neuro_simulation.GameScreen.systemManager;

public class FoodSystem extends SimpleSystem {
    private long last = System.currentTimeMillis();

    @Override
    public void update(float delta) {
        long current = System.currentTimeMillis();
        if (current - last >= 5) {
            last = current;
            systemManager.entitySystem.entities.add(
                    new Food(RandomUtil.nextFloat(-1000, 1000), RandomUtil.nextFloat(-1000, 1000),
                            RandomUtil.nextFloat(10, 20), RandomUtil.nextFloat(10, 20),
                            RandomUtil.nextFloat()));
        }
    }
}
