package com.jeekrs.neuro_simulation.sensories;

import com.jeekrs.neuro_simulation.entities.Living;
import com.jeekrs.neuro_simulation.utils.ImprovedNoise;
import com.jeekrs.neuro_simulation.utils.Package;

public class RandomSensory implements Sensory {
    @Override
    public Package detect(Living l) {
        return new Package((float) ImprovedNoise.noise(System.currentTimeMillis(), 0, 0));
    }

    @Override
    public Sensory clone() {
        return new RandomSensory();
    }
}
