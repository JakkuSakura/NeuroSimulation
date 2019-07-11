package com.jeekrs.neuro_simulation.entities;

import com.jeekrs.neuro_simulation.effectors.Legs;
import com.jeekrs.neuro_simulation.processors.NeuroProcessor;
import com.jeekrs.neuro_simulation.sensories.NearbyLivingSensory;
import com.jeekrs.neuro_simulation.sensories.NearbyWallSensory;
import com.jeekrs.neuro_simulation.sensories.RandomSensory;

public class Ant extends Living {
    static private int count = 0;

    public Ant() {
        super();
        setName("Ant" + (++count));
        addSensory(new NearbyLivingSensory());
        addSensory(new NearbyWallSensory());
        addSensory(new RandomSensory());
        Legs legs = new Legs();
        legs.setSpeedLimit(200);
        addEffector(legs);
        NeuroProcessor processor = new NeuroProcessor(this, 3, 5);
        setProcessor(processor);
        processor.init();
        System.out.println("Shuffling " + getName());
        processor.shuffle(5);


    }

}
