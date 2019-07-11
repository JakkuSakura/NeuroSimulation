package com.jeekrs.neuro_simulation.entities;

import com.badlogic.gdx.math.Vector2;
import com.jeekrs.neuro_simulation.component.Position;


public abstract class Entity implements Comparable<Entity>, Position, Cloneable {
    private Vector2 pos = new Vector2();

    @Override
    public Vector2 getPos() {
        return pos;
    }


    @Override
    public int compareTo(Entity e) {
        return Integer.compare(hashCode(), e.hashCode());
    }

    @Override
    public Entity clone() {
        try {
            Entity clone = (Entity) super.clone();

            clone.pos = new Vector2(pos);
            return clone;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

}




