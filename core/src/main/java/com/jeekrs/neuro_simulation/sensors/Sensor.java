package com.jeekrs.neuro_simulation.sensors;

import com.jeekrs.neuro_simulation.neuron_net.SensorNeuron;

public abstract class Sensor {
    public abstract int getNeuronNumber();
    public abstract SensorNeuron[] getSensorNeurons();
}
