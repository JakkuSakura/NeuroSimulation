package com.jeekrs.neuro_simulation.effectors;

import com.jeekrs.neuro_simulation.component.Movable;
import com.jeekrs.neuro_simulation.utils.Package;
import com.jeekrs.neuro_simulation.utils.Vec2d;

public class Legs implements Effector {

    private Movable movable;
    private double speedLimit;

    public Legs(Movable movable) {
        this.movable = movable;
    }

    private double softsign(double x) {
        return x / (1 + Math.abs(x));
    }

    private double adjust(double x) {
        return (x - 0.5) * 2;
    }

    @Override
    public void effect(Package p) {
        Vec2d v = new Vec2d(p.vals).apply(this::adjust);
        if (v.abs() > 1) {
            v.multiBy(1f / v.abs());
        }
        v.multiBy(getSpeedLimit());
        movable.getPhy().vel.set(v);
    }

    @Override
    public int neededLength() {
        return 2;
    }

    public double getSpeedLimit() {
        return speedLimit;
    }

    public void setSpeedLimit(double speedLimit) {
        this.speedLimit = speedLimit;
    }
}
