package com.jeekrs.neuro_simulation.neural_network;

public class SensorNeuron extends Neuron {
    float value = 0;
    @Override
    public float calculate() {
        return value;
    }

    @Override
    public void update(float v) {
        value = v;
    }

    @Override
    public void initiate() {
        // not to be cleaned up during preprocess
        // as value set up early by sensors
    }
}
