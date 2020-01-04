package com.jeekrs.neuro_simulation.components.effectors;

import com.jeekrs.neuro_simulation.components.Component;
import com.jeekrs.neuro_simulation.components.data.Vec2f;
import com.jeekrs.neural_network.dense.EffectorNeuron;
import com.jeekrs.neuro_simulation.utils.Cloner;

public class Legs extends Effector {
    public EffectorNeuron[] effector_neurons = {new EffectorNeuron(), new EffectorNeuron()};
    public float speed_limit = 40;
    public float speed = 20;

    @Override
    public int getNeuronNumber() {
        return 2;
    }

    @Override
    public EffectorNeuron[] getEffectorNeurons() {
        return effector_neurons;
    }

    public Vec2f velocity() {
        return new Vec2f(effector_neurons[0].v, effector_neurons[1].v).scl(speed).limit(speed_limit);
//        return new Vec2f(effector_neurons[0].v, effector_neurons[1].v).limit(speed_limit);

    }

    @Override
    public Component clone() {
        Legs legs = (Legs) super.clone();
        legs.effector_neurons = (EffectorNeuron[]) Cloner.copyArray(effector_neurons);
        return legs;
    }
}
