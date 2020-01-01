package com.jeekrs.neuro_simulation.entities;

import com.jeekrs.neuro_simulation.components.Edible;
import com.jeekrs.neuro_simulation.components.Hittable;
import com.jeekrs.neuro_simulation.components.Position;

public class Food extends Entity {
    public Food(float x, float y) {
        Edible c1 = new Edible(10, 2, 0);
        Position c = new Position(x, y);

        putComponent("food", c1);
        putComponent("pos", c);
        putComponent("hittable", new Hittable() {
            @Override
            public boolean contains(float x, float y) {
                return (c.x - x) * (c.x - x) + (c.y - y) * (c.y - y) <= c1.energy * c1.energy;
            }
        });
    }

    public Position getPosition() {
        return getComponentByName("pos").as();
    }

    public Edible getFood() {
        return getComponentByName("food").as();
    }
}
