package com.jeekrs.neuro_simulation.neuron_net;

public class Sigmoid implements Function {
    @Override
    public float run(float input) {
        return (float) (1.0 / (1 - Math.exp(-input)));
    }
}
