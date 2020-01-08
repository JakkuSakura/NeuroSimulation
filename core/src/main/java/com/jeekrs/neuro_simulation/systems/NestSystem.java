package com.jeekrs.neuro_simulation.systems;

import com.jeekrs.neuro_simulation.components.data.Position;
import com.jeekrs.neuro_simulation.entities.Ant;
import com.jeekrs.neuro_simulation.entities.AntNest;
import com.jeekrs.neuro_simulation.entities.Entity;
import com.jeekrs.neuro_simulation.utils.RandomUtil;

import static com.jeekrs.neuro_simulation.GameScreen.systemManager;

public class NestSystem extends SimpleSystem {
    private long last = System.currentTimeMillis();

    @Override
    public void update(float delta) {
        long current = System.currentTimeMillis();
        if (current - last >= 500) {
            last = current;
            for (Entity e : systemManager.entitySystem.populations.get(AntNest.class).getEntities()) {
                AntNest antNest = (AntNest) e;
                for (Entity entity : systemManager.entitySystem.populations.get(Ant.class).getEntities())
                    if (((Ant) entity).antNest == antNest)
                        antNest.updateBest((Ant) entity);

                Position pos = Position.getPosition(antNest);

                for (int i = 0; i < 50; i++) {
                    Ant ant;
                    if (antNest.best == null) {
                        ant = new Ant(pos.x, pos.y);
                        antNest.best = (Ant) ant.clone();
                    } else {
                        ant = (Ant) antNest.best.clone();
                        ant.putComponent("fighting", ant.newFighting());
                        ant.pos().x = pos.x;
                        ant.pos().y = pos.y;
                        ant.network().mutate();
                    }
                    ant.antNest = antNest;
                    systemManager.entitySystem.populations.get(Ant.class).addNewborn(ant);
                }

            }

        }
    }
}
