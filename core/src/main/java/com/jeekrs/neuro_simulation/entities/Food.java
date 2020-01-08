package com.jeekrs.neuro_simulation.entities;

import com.jeekrs.neuro_simulation.components.abilities.Edible;
import com.jeekrs.neuro_simulation.components.abilities.Hittable;
import com.jeekrs.neuro_simulation.components.data.Position;

public class Food extends Entity implements Hittable{
    public Food(float x, float y, float energy, float health, float type) {
        Edible c1 = new Edible(energy, health, type);
        Position c = new Position(x, y);

        putComponent("food", c1);
        putComponent("pos", c);

    }
    @Override
    public boolean contains(float x, float y) {
        Position c = Position.getPosition(this);
        float radius = getEdible().energy;
        return (c.x - x) * (c.x - x) + (c.y - y) * (c.y - y) <= radius;
    }


    public Edible getEdible() {
        return getComponentByNameAndClass("food", Edible.class);
    }
}
