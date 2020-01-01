package com.jeekrs.neuro_simulation.components.abilities;

import com.jeekrs.neuro_simulation.components.Component;

public class Edible extends Component {
    public float energy;
    public float health;
    public float type;
    public Edible(float e, float h, float t) {
        energy = e;
        health = h;
        type = t;
    }
}
