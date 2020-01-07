package com.jeekrs.neuro_simulation.entities;

import com.jeekrs.neuro_simulation.components.abilities.Edible;
import com.jeekrs.neuro_simulation.components.abilities.Hittable;
import com.jeekrs.neuro_simulation.components.data.Position;

public class Food extends Entity {
    public Food(float x, float y, float energy, float health, float type) {
        Edible c1 = new Edible(energy, health, type);
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


    public Edible getEdible() {
        return getComponentByNameAndClass("food", Edible.class);
    }
}
