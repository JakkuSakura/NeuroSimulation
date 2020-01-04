package com.jeekrs.neuro_simulation.components.effectors;

import com.jeekrs.neuro_simulation.components.Component;
import com.jeekrs.neural_network.dense.EffectorNeuron;
import com.jeekrs.neuro_simulation.utils.Cloner;

public class Reproduction extends Effector {
    public EffectorNeuron[] effector_neurons = {new EffectorNeuron()};
    public float threshold = 0.8f;
    public float energy_consumption;

    public Reproduction(float energy_consumption) {
        this.energy_consumption = energy_consumption;
    }

    @Override
    public int getNeuronNumber() {
        return 1;
    }

    @Override
    public EffectorNeuron[] getEffectorNeurons() {
        return effector_neurons;
    }

    @Override
    public Component clone() {
        Reproduction clone = (Reproduction) super.clone();
        clone.effector_neurons = (EffectorNeuron[]) Cloner.copyArray(effector_neurons);
        return clone;
    }
}
