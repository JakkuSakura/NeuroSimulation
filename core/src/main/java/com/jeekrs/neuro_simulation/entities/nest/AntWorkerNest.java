package com.jeekrs.neuro_simulation.entities.nest;

import com.jeekrs.neuro_simulation.entities.livings.AntWorker;

public class AntWorkerNest extends Nest {
    public AntWorkerNest() {

        setPrototype(new AntWorker());
        setDelayMs(500);
    }

    @Override
    public void evolve() {
        getPrototype().getProcessor().adjust(0.3f);
    }
}
