package com.jeekrs.neuro_simulation.sensories;

import com.jeekrs.neuro_simulation.component.MyCloneable;
import com.jeekrs.neuro_simulation.entities.Living;
import com.jeekrs.neuro_simulation.utils.Package;

public interface Sensory extends MyCloneable {
    Package detect(Living living);

    Sensory clone();
}
