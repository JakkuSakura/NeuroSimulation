package com.jeekrs.neuro_simulation.entities;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.jeekrs.neuro_simulation.interfaces.Position;


public abstract class Entity implements Comparable<Entity>, Position, Cloneable {
    private Vector2 pos = new Vector2();
    private float health_limit;
    private float health;


    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

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

    public float getHealth() {
        return health;
    }

    public void setHealth(float health) {
        this.health = health;
    }

    public float getHealthLimit() {
        return health_limit;
    }

    public void setHealthLimit(float health_limit) {
        this.health_limit = health_limit;
    }

    public abstract boolean canEat();

    public abstract boolean overlaps(Circle circle);
}




