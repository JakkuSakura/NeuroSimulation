package com.jeekrs.neuro_simulation.system;

import com.badlogic.gdx.math.Intersector;
import com.jeekrs.neuro_simulation.Agenda;
import com.jeekrs.neuro_simulation.entities.Entity;
import com.jeekrs.neuro_simulation.entities.functionals.Food;
import com.jeekrs.neuro_simulation.entities.functionals.Wood;
import com.jeekrs.neuro_simulation.entities.livings.Living;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.jeekrs.neuro_simulation.GameScreen.systemManager;

public class ResourceSystem extends SimpleSystem {
    public Map<Agenda, Resource> mapping = new HashMap<>();

    @Override
    public void init() {
        systemManager.agendaSystem.agendas.forEach((a, s) -> mapping.put(a, new Resource(100)));
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
            if (entity instanceof Wood) {
                Wood n = (Wood) entity;
                if (System.currentTimeMillis() - n.getLastTime() >= n.getDelayMs()) {
                    n.setLastTime(System.currentTimeMillis());
                    systemManager.entitySystem.addEntity(n.produceFood());
                }
            }
        }

        for (Entity entity : systemManager.entitySystem.entities) {
            if (entity instanceof Living) {
                Living living = (Living) entity;
                for (Entity e2 : systemManager.entitySystem.entities) {
                    if (e2 instanceof Food) {
                        Food food = (Food) e2;
                        if (!food.isPicked()) {
                            if (Intersector.overlaps(living.getCircle(), food.getCircle())) {
                                food.setPicked(true);
                                addFood(living.getAgenda(), food.getValue());
                                systemManager.entitySystem.dead.add(food);
                            }

                        }
                    }
                }
            }
        }
    }
}
