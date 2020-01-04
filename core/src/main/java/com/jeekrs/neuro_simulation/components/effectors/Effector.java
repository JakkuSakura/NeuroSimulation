package com.jeekrs.neuro_simulation.components.effectors;

import com.jeekrs.neuro_simulation.components.Component;
import com.jeekrs.neural_network.dense.EffectorNeuron;

public abstract class Effector extends Component {
    public abstract int getNeuronNumber();
    public abstract EffectorNeuron[] getEffectorNeurons();
}
