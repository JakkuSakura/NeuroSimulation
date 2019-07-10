package com.jeekrs.neuro_simulation.sensories;

import com.jeekrs.neuro_simulation.utils.Package;
import com.jeekrs.neuro_simulation.utils.RandomUtil;

public class RandomSensory implements Sensory {
    @Override
    public Package detect() {
        return new Package(RandomUtil.nextDouble());
    }
}
