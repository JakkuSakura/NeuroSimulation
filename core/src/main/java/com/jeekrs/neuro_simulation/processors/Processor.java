package com.jeekrs.neuro_simulation.processors;

import com.jeekrs.neuro_simulation.utils.Package;

public interface Processor {
    Package process(Package p);

    void init();
}
