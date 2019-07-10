package com.jeekrs.neuro_simulation.entities;

import com.badlogic.gdx.math.Vector2;
import com.jeekrs.neuro_simulation.component.HasPhysics;

public class Structure extends Entity implements HasPhysics {
    @Override
    public Vector2 getPos() {
        return phy.pos;
    }

    @Override
    public Vector2 getVel() {
        return phy.vel;
    }

    @Override
    public Vector2 getAcc() {
        return phy.acc;
    }
}
