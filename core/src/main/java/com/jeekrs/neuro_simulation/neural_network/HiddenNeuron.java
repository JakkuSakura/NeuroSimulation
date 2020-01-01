package com.jeekrs.neuro_simulation.neural_network;

public class HiddenNeuron extends Neuron {
    public Function func = new Sigmoid();
    public float v;
    @Override
    public float calculate() {
        return func.run(v);
    }

    @Override
    public void update(float v) {
        this.v += v;
    }

    @Override
    public void initiate() {
        v = 0;
    }
}
