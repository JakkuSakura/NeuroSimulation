package com.jeekrs.neuro_simulation.entities.livings;

import com.jeekrs.neuro_simulation.interfaces.Breedable;
import com.jeekrs.neuro_simulation.processors.NeuroProcessor;
import com.jeekrs.neuro_simulation.processors.effectors.Legs;
import com.jeekrs.neuro_simulation.processors.sensories.NearbyLivingSensory;
import com.jeekrs.neuro_simulation.processors.sensories.NearbyWallSensory;
import com.jeekrs.neuro_simulation.processors.sensories.RandomSensory;

public class AntWorker extends NeuralLiving implements Breedable {
    static private int count = 0;

    public AntWorker() {
        super();
        setName("AntFighter" + (++count));
        addSensory(new NearbyLivingSensory());
        addSensory(new NearbyWallSensory());
        addSensory(new RandomSensory());
        Legs legs = new Legs();
        legs.setSpeedLimit(200);
        addEffector(legs);
        NeuroProcessor processor = new NeuroProcessor(this, 3, 5);
        setProcessor(processor);
        processor.init();
        setDamage(30);
        setDefence(15);
        System.out.println("Shuffling " + getName());
        processor.shuffle(5);

    }

    public AntWorker breed() {
        return (AntWorker) super.breed();
    }
}
