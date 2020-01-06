package com.jeekrs.neuro_simulation.components.sensors;

import com.jeekrs.neuro_simulation.components.Component;

public abstract class Sensor extends Component {
    public abstract int getInputNumber();
    public abstract float[] getInputs();
}
