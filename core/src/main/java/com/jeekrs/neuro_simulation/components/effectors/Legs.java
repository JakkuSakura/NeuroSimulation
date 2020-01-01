package com.jeekrs.neuro_simulation.components.effectors;

import com.jeekrs.neuro_simulation.components.data.Vec2f;
import com.jeekrs.neuro_simulation.neural_network.EffectorNeuron;

public class Legs extends Effector {
    public EffectorNeuron[] effector_neurons = new EffectorNeuron[]
            {
                    new EffectorNeuron(), new EffectorNeuron()
            };
    public float speed_limit = 20;

    @Override
    public int getNeuronNumber() {
        return 2;
    }

    @Override
    public EffectorNeuron[] getEffectorNeurons() {
        return effector_neurons;
    }

    public Vec2f velocity() {
        return new Vec2f(effector_neurons[0].v, effector_neurons[1].v).limit(speed_limit);

    }
}
