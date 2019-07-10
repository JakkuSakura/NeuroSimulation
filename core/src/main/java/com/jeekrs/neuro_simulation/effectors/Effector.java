package com.jeekrs.neuro_simulation.effectors;

import com.jeekrs.neuro_simulation.component.MyCloneable;
import com.jeekrs.neuro_simulation.entities.Living;
import com.jeekrs.neuro_simulation.utils.Package;

public interface Effector extends MyCloneable {
    Effector clone();

    void effect(Package p, Living l);
    int neededLength();
}
