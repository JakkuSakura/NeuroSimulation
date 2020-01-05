package com.jeekrs.neural_network.dense;

import com.jeekrs.neuro_simulation.interfaces.PublicClonable;

import java.util.ArrayList;

public abstract class Neuron implements PublicClonable<Neuron> {
    public ArrayList<Integer> input = new ArrayList<>(), output = new ArrayList<>();

    public abstract float calculate();
    public abstract float value();
    public abstract void update(float v);
    public abstract void initiate();

    @Override
    public Neuron clone() {
        try {
            return (Neuron)super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
