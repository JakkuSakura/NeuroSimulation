package com.jeekrs.neuro_simulation.components.abilities;

import com.jeekrs.neuro_simulation.components.Component;

public class Hungry extends Component {
    public float hungry_rate;

    public Hungry(float hungry_rate) {
        this.hungry_rate = hungry_rate;
    }
}
