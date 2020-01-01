package com.jeekrs.neuro_simulation.neural_network;

public class Sigmoid implements Function {
    @Override
    public float run(float input) {
        return (float) (1.0 / (1 + Math.exp(-input)));
    }
}
