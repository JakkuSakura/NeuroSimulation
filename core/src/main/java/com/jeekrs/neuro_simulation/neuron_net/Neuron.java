package com.jeekrs.neuro_simulation.neuron_net;

import java.util.ArrayList;

public abstract class Neuron {
    public ArrayList<Link> input, output;

    abstract public float calculate();
}
