package com.jeekrs.neuro_simulation.neuron_net;

import java.util.ArrayList;

public abstract class Neuron {
    public ArrayList<Link> input = new ArrayList<>(), output = new ArrayList<>();

    public abstract float calculate();

    public abstract void update(float v);

    public abstract void initiate();
}
