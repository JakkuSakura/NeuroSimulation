package com.jeekrs.neuro_simulation.entities.livings;

import com.jeekrs.neuro_simulation.interfaces.Breedable;
import com.jeekrs.neuro_simulation.processors.NeuroProcessor;
import com.jeekrs.neuro_simulation.processors.effectors.Legs;
import com.jeekrs.neuro_simulation.processors.sensories.NearbyLivingSensory;
import com.jeekrs.neuro_simulation.processors.sensories.NearbyWallSensory;
import com.jeekrs.neuro_simulation.processors.sensories.RandomSensory;

public class AntFighter extends NeuralLiving implements Breedable {
    static private int count = 0;

    public AntFighter() {
        super();
        setName("AntFighter" + (++count));
        addSensory(new NearbyLivingSensory());
        addSensory(new NearbyWallSensory());
        addSensory(new RandomSensory());
        Legs legs = new Legs();
        legs.setSpeedLimit(300);
        addEffector(legs);
        NeuroProcessor processor = new NeuroProcessor(this, 3, 5);
        setProcessor(processor);
        processor.init();
        setDamage(50);
        setDefence(50);
        System.out.println("Shuffling " + getName());
        processor.shuffle(5);

    }

    public AntFighter breed() {
        return (AntFighter) super.breed();
    }
}
