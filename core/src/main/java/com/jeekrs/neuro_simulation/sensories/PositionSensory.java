package com.jeekrs.neuro_simulation.sensories;

import com.jeekrs.neuro_simulation.entities.Living;
import com.jeekrs.neuro_simulation.utils.Package;
import com.jeekrs.neuro_simulation.utils.Vec2d;

public class PositionSensory implements Sensory{
    private Living living;
    public PositionSensory (Living living)
    {
        this.living = living;
    }
    @Override
    public Package detect() {
        Vec2d pos = living.getPhy().pos;
        Package p = new Package();
        p.vals.add(pos.x);
        p.vals.add(pos.y);
        return p;
    }
}
