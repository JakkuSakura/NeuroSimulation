package com.jeekrs.neuro_simulation.neural_network;

import com.jeekrs.neuro_simulation.interfaces.PublicClonable;

public class Link implements PublicClonable<Link> {
    public int form, to;
    public float weight;

    @Override
    public Link clone() {
        try {
            return (Link) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
