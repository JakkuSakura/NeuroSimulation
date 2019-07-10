package com.jeekrs.neuro_simulation.entities;

import com.jeekrs.neuro_simulation.effectors.Legs;
import com.jeekrs.neuro_simulation.processors.NeuroProcessor;
import com.jeekrs.neuro_simulation.sensories.PositionSensory;
import com.jeekrs.neuro_simulation.sensories.RandomSensory;

public class Ant extends Living {
    static private int count = 0;

    public Ant() {
        super();
        addSensory(new PositionSensory(this));
        addSensory(new RandomSensory());
        Legs legs = new Legs(this);
        legs.setSpeedLimit(100);
        addEffector(legs);
        setProcessor(new NeuroProcessor(this, 10, 10));
        initProcessor();
        setName("Ant" + (++count));
    }
}
