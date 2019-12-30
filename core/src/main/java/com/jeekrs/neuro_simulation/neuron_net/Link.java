package com.jeekrs.neuro_simulation.neuron_net;

public class Link {
    public Neuron form, to;
    public float weight;
    static public Link makeLink(Neuron f, Neuron t, int weight) {
        Link link = new Link();
        link.form = f;
        link.to = t;
        link.weight = weight;
        f.output.add(link);
        t.input.add(link);
        return link;
    }
}
