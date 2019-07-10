package com.jeekrs.neuro_simulation.entities;

import com.jeekrs.neuro_simulation.component.HasPhysics;
import com.jeekrs.neuro_simulation.component.Physics;

public abstract class Entity implements Comparable<Entity>, HasPhysics {
    private Physics phy = new Physics();

    @Override
    public Physics getPhy() {
        return phy;
    }
    @Override
    public int compareTo(Entity e)
    {
        return Integer.compare(hashCode(), e.hashCode());
    }

}
