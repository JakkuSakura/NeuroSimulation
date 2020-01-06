package com.jeekrs.neuro_simulation.components.sensors;

import com.jeekrs.neuro_simulation.utils.RandomUtil;

public class RandomSensor extends Sensor {
    @Override
    public int getInputNumber() {
        return 1;
    }

    @Override
    public float[] getInputs() {
        float[] x = new float[1];
        x[0] = RandomUtil.nextFloat();
        return x;
    }
}
