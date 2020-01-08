package com.jeekrs.neuro_simulation.systems;

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
            if (systemManager.entitySystem.populations.get(Food.class).getEntities().size() < 1000)
                for (int i = 0; i < 10; i++) {
                    systemManager.entitySystem.populations.get(Food.class).addNewborn(
                            new Food(RandomUtil.nextFloat(-2000, 2000), RandomUtil.nextFloat(-2000, 2000),
                                    RandomUtil.nextFloat(10, 20), RandomUtil.nextFloat(10, 20),
                                    RandomUtil.nextFloat()));

                }
        }
    }
}
