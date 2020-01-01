package com.jeekrs.neuro_simulation.components.abilities;

import com.jeekrs.neuro_simulation.components.Component;

public abstract class Hittable extends Component {
    public abstract boolean contains(float x, float y);
}
