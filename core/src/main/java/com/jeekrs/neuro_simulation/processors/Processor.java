package com.jeekrs.neuro_simulation.processors;

import com.jeekrs.neuro_simulation.utils.Package;

public interface Processor extends Cloneable {
    Package process(Package p);

    Processor clone();
    void init();
}
