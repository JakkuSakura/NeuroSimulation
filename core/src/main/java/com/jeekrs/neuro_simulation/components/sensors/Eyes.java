package com.jeekrs.neuro_simulation.components.sensors;

import com.jeekrs.neuro_simulation.components.Component;

public class Eyes extends Sensor{
    private static final int SENSOR_NEURON_NUM = 4;
    public float[] inputs = new float[SENSOR_NEURON_NUM];

    public float limit;

    public Eyes(float limit) {
        this.limit = limit;
    }

    @Override
    public int getInputNumber() {
        return SENSOR_NEURON_NUM;
    }

    @Override
    public float[] getInputs() {
        return inputs;
    }

    @Override
    public Component clone() {
        Eyes eyes = (Eyes) super.clone();
        eyes.inputs = new float[SENSOR_NEURON_NUM];
        System.arraycopy(this.inputs, 0, eyes.inputs, 0, SENSOR_NEURON_NUM);
        return eyes;
    }
}
