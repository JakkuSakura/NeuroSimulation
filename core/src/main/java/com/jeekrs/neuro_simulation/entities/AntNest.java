package com.jeekrs.neuro_simulation.entities;

import com.jeekrs.neuro_simulation.components.abilities.Hittable;
import com.jeekrs.neuro_simulation.components.data.Position;

public class AntNest extends Entity {
    public Ant best;
    // todo add best fit
    public AntNest(float x, float y) {
        Position c = new Position(x, y);
        putComponent("pos", c);
        putComponent("hittable", new Hittable() {
            @Override
            public boolean contains(float x, float y) {
                Position pos = Position.getPosition(AntNest.this);
                return x >= pos.x - 20 && x <= pos.x + 20 && y >= pos.y - 20 && y <= pos.y + 20;
            }
        });
    }

}
