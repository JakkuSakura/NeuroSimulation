package com.jeekrs.neuro_simulation.effectors;

import com.jeekrs.neuro_simulation.neuron_net.EffectorNeuron;

public abstract class Effector {
    public abstract int getNeuronNumber();
    public abstract EffectorNeuron[] getEffectorNeurons();
}
