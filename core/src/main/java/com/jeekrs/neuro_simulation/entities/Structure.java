package com.jeekrs.neuro_simulation.entities;

import com.jeekrs.neuro_simulation.component.HasPhysics;
import com.jeekrs.neuro_simulation.component.Physics;

public class Structure extends Entity implements HasPhysics {
    private Physics phy = new Physics();
    @Override
    public Physics getPhy() {
        return phy;
    }
}
