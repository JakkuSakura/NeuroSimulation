package com.jeekrs.neuro_simulation.game;

import com.jeekrs.neuro_simulation.components.data.Position;
import com.jeekrs.neuro_simulation.entities.Ant;
import com.jeekrs.neuro_simulation.entities.AntNest;
import com.jeekrs.neuro_simulation.entities.Entity;

import static com.jeekrs.neuro_simulation.GameScreen.systemManager;

public class NestSystem extends SimpleSystem {
    private long last = System.currentTimeMillis();

    @Override
    public void update(float delta) {
        long current = System.currentTimeMillis();
        if (current - last >= 500) {
            last = current;
            for (Entity e : systemManager.worldSystem.populations.get(AntNest.class).getEntities()) {
                AntNest antNest = (AntNest) e;

                boolean updated = systemManager.worldSystem.populations.get(Ant.class).getEntities().stream()
                        .filter(entity -> ((Ant) entity).antNest == antNest)
                        .map(entity -> antNest.updateBest((Ant) entity))
                        .reduce(false, (a, b) -> a || b);

                if (updated)
                    antNest.best = (Ant) antNest.best.clone();

                Position pos = Position.getPosition(antNest);

                for (int i = 0; i < 10; i++) {
                    Ant ant;
                    if (antNest.best == null) {
                        ant = new Ant(pos.x, pos.y);
                        antNest.best = (Ant) ant.clone();
                    } else {
                        ant = (Ant) antNest.best.clone();
                        ant.putComponent("fighting", ant.newFighting());
                        ant.pos().x = pos.x;
                        ant.pos().y = pos.y;
                        for (int j = 0; j < 5; j++) {
                            ant.network().mutate();
                        }
                    }
                    ant.antNest = antNest;
                    systemManager.worldSystem.populations.get(Ant.class).addNewborn(ant);
                }

            }

        }
    }
}
