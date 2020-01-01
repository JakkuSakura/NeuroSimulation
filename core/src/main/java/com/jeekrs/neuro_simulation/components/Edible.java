package com.jeekrs.neuro_simulation.components;

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
