package com.jeekrs.neuro_simulation.neural_network;

public class Link {
    public Neuron form, to;
    public float weight;
    static public Link makeLink(Neuron f, Neuron t, float weight) {
        Link link = new Link();
        link.form = f;
        link.to = t;
        link.weight = weight;
        f.output.add(link);
        t.input.add(link);
        return link;
    }
}
