package com.jeekrs.neural_network.dense;

public class HiddenNeuron extends Neuron {
    protected Function func = new Sigmoid();
    protected float v;

    @Override
    public float calculate() {
        return func.run(v);
    }

    @Override
    public float value() {
        return v;
    }

    @Override
    public void update(float v) {
        this.v += v;
    }

    @Override
    public void initiate() {
        v = 0;
    }

    @Override
    public String toString() {
        return "HiddenNeuron@" + hashCode() + "{" +
                "func=" + func +
                ", v=" + v +
                '}';
    }
}
