package com.jeekrs.neuro_simulation.components.sensors;

public class HealthSensor extends Sensor {
    public float[] inputs = new float[2];
    @Override
    public int getInputNumber() {
        return 2;
    }

    @Override
    public float[] getInputs() {
        return inputs;
    }
}
