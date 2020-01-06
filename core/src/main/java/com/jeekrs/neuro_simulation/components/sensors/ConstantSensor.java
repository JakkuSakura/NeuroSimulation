package com.jeekrs.neuro_simulation.components.sensors;

public class ConstantSensor extends Sensor {

    @Override
    public int getInputNumber() {
        return 1;
    }

    @Override
    public float[] getInputs() {
        return new float[]{1f};
    }
}
