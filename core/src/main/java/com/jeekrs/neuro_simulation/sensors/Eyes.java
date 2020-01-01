package com.jeekrs.neuro_simulation.sensors;

import com.jeekrs.neuro_simulation.neuron_net.SensorNeuron;

public class Eyes extends Sensor{

    @Override
    public int getNeuronNumber() {
        return 0;
    }

    @Override
    public SensorNeuron[] getSensorNeurons() {
        return new SensorNeuron[0];
    }
}
