package com.jeekrs.neural_network.dense;

public class Sigmoid implements Function {
    @Override
    public String toString() {
        return Sigmoid.class.getSimpleName();
    }

    @Override
    public float run(float input) {
        return (float) (1.0 / (1 + Math.exp(-input)));
    }
}
