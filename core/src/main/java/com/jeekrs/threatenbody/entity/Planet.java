package com.jeekrs.threatenbody.entity;

import com.jeekrs.threatenbody.component.PhysicsComponent;
import com.jeekrs.threatenbody.interfaces.Collidable;
import com.jeekrs.threatenbody.interfaces.Gravity;
import com.jeekrs.threatenbody.interfaces.Renderable;

public class Planet implements Entity, Gravity, Collidable, Renderable {
    public PhysicsComponent physicsComponent = new PhysicsComponent(this);
    public float radius;
    public float pho;

    public Planet(float radius, float pho) {
        this.radius = radius;
        this.pho = pho;
    }

    public double getMass() {
        return (float) (radius * radius * Math.PI * pho);
    }


    @Override
    public float getCollidingRadius(Collidable other) {
        return radius;
    }


    @Override
    public PhysicsComponent getPhysicsComponent() {
        return physicsComponent;
    }
}
