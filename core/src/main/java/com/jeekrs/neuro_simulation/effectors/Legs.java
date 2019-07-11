package com.jeekrs.neuro_simulation.effectors;

import com.badlogic.gdx.math.Vector2;
import com.jeekrs.neuro_simulation.entities.Living;
import com.jeekrs.neuro_simulation.utils.Package;

public class Legs implements Effector {

    private float speedLimit;

    private float softsign(float x) {
        return x / (1 + Math.abs(x));
    }

    private float adjust(float x) {
        return (float) ((x - 0.5) * 2);
    }

    @Override
    public Effector clone() {
        Object clone = null;
        try {
            clone = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return (Effector) clone;
    }

    @Override
    public void effect(Package p, Living l) {

        l.setDirection(p.vals.get(0) * 360);
        Vector2 v = new Vector2(-(float) Math.sin(l.getDirection() * Math.PI / 180) * p.vals.get(1) * getSpeedLimit(),
                (float) Math.cos(l.getDirection() * Math.PI / 180) * p.vals.get(1) * getSpeedLimit());
        l.getVel().set(v);
    }

    @Override
    public int neededLength() {
        return 2;
    }

    public float getSpeedLimit() {
        return speedLimit;
    }

    public void setSpeedLimit(float speedLimit) {
        this.speedLimit = speedLimit;
    }

}
