package com.jeekrs.neuro_simulation.components.effectors;

import com.jeekrs.neuro_simulation.components.Component;
import com.jeekrs.neuro_simulation.components.data.Vec2f;

public class Legs extends Effector {
    private float[] outputs = new float[2];
    public float speed_limit = 40;
    public float speed = 20;
    public float vx = 0;
    public float vy = 0;
    public static final int OUTPUT_NUMBER = 2;
    @Override
    public int getOutputNumber() {
        return OUTPUT_NUMBER;
    }

    @Override
    public float[] getOutputs() {
        return outputs;
    }

    public Vec2f velocity() {
        return new Vec2f(vx, vy).scl(speed).limit(speed_limit);
    }

    @Override
    public Component clone() {
        Legs legs = (Legs) super.clone();
        legs.outputs = new float[OUTPUT_NUMBER];
        System.arraycopy(this.outputs, 0, legs.outputs, 0, OUTPUT_NUMBER);
        return legs;
    }

    public void calculateAcceleration(float delta) {
        this.vx += outputs[0] * delta;
        this.vy += outputs[1] * delta;
    }
}
