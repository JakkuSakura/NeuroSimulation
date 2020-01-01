package com.jeekrs.neuro_simulation.components.sensors;

import com.jeekrs.neuro_simulation.neural_network.SensorNeuron;

public class Eyes extends Sensor{
    public SensorNeuron[] neurons = new SensorNeuron[10];
    public float limit = 100;

    public Eyes() {
        for (int i = 0; i < 10; ++i)
            neurons[i] = new SensorNeuron();
    }
    @Override
    public int getNeuronNumber() {
        return 10;
    }

    @Override
    public SensorNeuron[] getSensorNeurons() {
        return neurons;
    }
}
