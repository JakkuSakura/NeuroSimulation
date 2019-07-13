package com.jeekrs.neuro_simulation.processors.sensories;

import com.jeekrs.neuro_simulation.entities.livings.Living;
import com.jeekrs.neuro_simulation.utils.Package;
import com.jeekrs.neuro_simulation.utils.PerlinNoise;

public class RandomSensory implements Sensory {
    @Override
    public Package detect(Living l) {
        return new Package((float) PerlinNoise.noise(System.currentTimeMillis(), 0, 0));
    }

    @Override
    public Sensory clone() {
        return new RandomSensory();
    }
}
