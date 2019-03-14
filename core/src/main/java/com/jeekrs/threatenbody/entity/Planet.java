package com.jeekrs.threatenbody.entity;

import com.jeekrs.threatenbody.component.CivilazationComponent;
import com.jeekrs.threatenbody.component.OreComponent;
import com.jeekrs.threatenbody.interfaces.Collidable;
import com.jeekrs.threatenbody.interfaces.Gravity;
import com.jeekrs.threatenbody.interfaces.Renderable;
import com.jeekrs.threatenbody.utils.Vec2d;

public class Planet implements Entity, Gravity, Collidable, Renderable {
    public Vec2d acc = new Vec2d(), vel = new Vec2d(), pos = new Vec2d();
    public CivilazationComponent civil = new CivilazationComponent();
    public OreComponent ores = new OreComponent();
    public double radius;
    public double mass;

    public Planet(double radius, double mass) {
        this.radius = radius;
        this.mass = mass;
    }

    public double getMass() {
        return mass;
    }


    @Override
    public double getRadius() {
        return radius;
    }

    @Override
    public Vec2d getAcc() {
        return acc;
    }

    @Override
    public Vec2d getPos() {
        return pos;
    }

    @Override
    public Vec2d getVel() {
        return vel;
    }


    @Override
    public int compareTo(Entity entity) {
        return Integer.compare(this.hashCode(), entity.hashCode());
    }
}
