package com.jeekrs.neuro_simulation.components.effectors;

import com.jeekrs.neuro_simulation.components.Component;
import com.jeekrs.neural_network.dense.EffectorNeuron;
import com.jeekrs.neuro_simulation.utils.Cloner;

public class Reproduction extends Effector {
    public float[] outputs = new float[1];
    public float threshold = 0.8f;
    public float energy_consumption;

    public Reproduction(float energy_consumption) {
        this.energy_consumption = energy_consumption;
    }

    @Override
    public int getOutputNumber() {
        return 1;
    }

    @Override
    public float[] getOutputs() {
        return outputs;
    }

    @Override
    public Component clone() {
        Reproduction clone = (Reproduction) super.clone();
        clone.outputs = new float[1];
        clone.outputs[0] = this.outputs[0];
        return clone;
    }
}
