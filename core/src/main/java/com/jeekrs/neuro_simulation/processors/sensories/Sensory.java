package com.jeekrs.neuro_simulation.processors.sensories;

import com.jeekrs.neuro_simulation.entities.livings.Living;
import com.jeekrs.neuro_simulation.interfaces.MyCloneable;
import com.jeekrs.neuro_simulation.utils.Package;

public interface Sensory extends MyCloneable {
    Package detect(Living living);

    Sensory clone();
}
