package com.jeekrs.neuro_simulation.system;

import com.jeekrs.neuro_simulation.Agenda;
import com.jeekrs.neuro_simulation.entities.Entity;
import com.jeekrs.neuro_simulation.entities.functionals.Food;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.jeekrs.neuro_simulation.GameScreen.systemManager;

public class FoodSystem extends SimpleSystem {
    public Map<Agenda, Resource> mapping = new HashMap<>();

    @Override
    public void init() {

    }

    public float getFood(Agenda a) {
        return Optional.ofNullable(mapping.get(a)).map(e -> e.food).orElse(0f);
    }

    public void addFood(Agenda e, float val) {
        Optional.ofNullable(mapping.get(e)).map(b -> b.food += val);
    }

    @Override
    public void update(float delta) {


        for (Entity entity : systemManager.entitySystem.entities) {
            if (entity.canEat()) {
                for (Entity e2 : systemManager.entitySystem.entities) {
                    if (e2 instanceof Food) {
                        Food food = (Food) e2;
                        if (!food.isPicked()) {
                            if (entity.overlaps(food.getCircle())) {
                                food.setPicked(true);
//                                entity.interact(food);
                                // todo
                                systemManager.entitySystem.dead.add(food);
                            }

                        }
                    }
                }
            }
        }
    }
}
