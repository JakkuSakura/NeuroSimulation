package com.jeekrs.neuro_simulation.entities;

import com.badlogic.gdx.math.Vector2;
import com.jeekrs.neuro_simulation.component.HasPhysics;
import com.jeekrs.neuro_simulation.component.Physics;

public abstract class Entity implements Comparable<Entity>, HasPhysics, Cloneable {
    protected Physics phy = new Physics();

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

    @Override
    public int compareTo(Entity e)
    {
        return Integer.compare(hashCode(), e.hashCode());
    }

    @Override
    public Entity clone() {
        try {
            Entity clone = (Entity) super.clone();
            clone.phy = phy.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

}




