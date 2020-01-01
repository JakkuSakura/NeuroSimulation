package com.jeekrs.neuro_simulation.components.data;

import com.jeekrs.neuro_simulation.entities.Entity;

public class Position extends Vec2f {
    public Position(float x, float y) {
        super(x, y);
    }

    public static boolean hasPosition(Entity entity) {
        return entity.hasComponentByNameAndClass("pos", Position.class);
    }

    public static Position getPosition(Entity x) {
        return x.getComponentByNameAndClass("pos", Position.class);
    }
}
