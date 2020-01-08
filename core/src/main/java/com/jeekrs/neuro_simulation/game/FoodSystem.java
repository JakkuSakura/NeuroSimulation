package com.jeekrs.neuro_simulation.game;

import com.jeekrs.neuro_simulation.components.abilities.Edible;
import com.jeekrs.neuro_simulation.components.abilities.Fighting;
import com.jeekrs.neuro_simulation.components.abilities.Hittable;
import com.jeekrs.neuro_simulation.components.abilities.Hungry;
import com.jeekrs.neuro_simulation.components.data.Position;
import com.jeekrs.neuro_simulation.entities.Entity;
import com.jeekrs.neuro_simulation.entities.Food;

import com.jeekrs.neuro_simulation.utils.RandomUtil;

import java.util.ArrayList;

import static com.jeekrs.neuro_simulation.GameScreen.systemManager;

public class FoodSystem extends SimpleSystem {
    private long last = System.currentTimeMillis();

    @Override
    public void update(float delta) {
        long current = System.currentTimeMillis();
        if (current - last >= 5) {
            last = current;
            if (systemManager.worldSystem.populations.get(Food.class).getEntities().size() < 1000)
                for (int i = 0; i < 10; i++) {
                    systemManager.worldSystem.populations.get(Food.class).addNewborn(
                            new Food(RandomUtil.nextFloat(-2000, 2000), RandomUtil.nextFloat(-2000, 2000),
                                    RandomUtil.nextFloat(10, 20), RandomUtil.nextFloat(10, 20),
                                    RandomUtil.nextFloat()));

                }
        }


        consume();
        hungry(delta);
        starve();
    }
    private void consume() {
        ArrayList<Entity> food = systemManager.worldSystem.populations.get(Food.class).getEntities();
        systemManager.worldSystem.populations.values().forEach(population ->
                population.getEntities().stream().filter(entity -> entity instanceof Hittable && entity.hasComponentByClass(Fighting.class))
                        .forEach(entity -> {

                            for (Entity e : food) {
                                if (e.hasComponentByClass(Edible.class)) {
                                    Position food_pos = Position.getPosition(e);
                                    if (((Hittable) entity).contains(food_pos.x, food_pos.y)) {

                                        Fighting fighting = entity.getComponentByClass(Fighting.class);
                                        fighting.health += e.getComponentByClass(Edible.class).health;
                                        fighting.adjustHealth();
                                        fighting.energy += e.getComponentByClass(Edible.class).energy;
                                        fighting.adjustEnergy();

                                        e.removeComponent(e.getComponentByClass(Edible.class));
                                        systemManager.worldSystem.populations.get(Food.class).addDead(e);
                                    }
                                }
                            }
                        }));
    }

    private void hungry(float delta) {
        systemManager.worldSystem.populations.values().forEach(population ->
                population.getEntities().stream()
                        .filter(entity -> entity.hasComponentByClass(Hungry.class) && entity.hasComponentByClass(Fighting.class))
                        .forEach(entity -> entity.getComponentByClass(Fighting.class).health -= entity.getComponentByClass(Hungry.class).hungry_rate * delta));
    }

    private void starve() {
        systemManager.worldSystem.populations.values().forEach(population ->
                population.getEntities().stream()
                        .filter(x -> x.hasComponentByClass(Fighting.class))
                        .filter(x -> x.getComponentByClass(Fighting.class).health <= 0)
                        .forEach(population::addDead));
    }

}
