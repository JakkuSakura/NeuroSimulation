package com.jeekrs.neuro_simulation.components.effectors;

import com.jeekrs.neuro_simulation.components.Component;

public abstract class Effector extends Component {
    public abstract int getOutputNumber();
    public abstract float[] getOutputs();
}
