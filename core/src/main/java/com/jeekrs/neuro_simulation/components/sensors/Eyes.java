package com.jeekrs.neuro_simulation.components.sensors;

import com.jeekrs.neuro_simulation.components.Component;
import com.jeekrs.neural_network.dense.SensorNeuron;
import com.jeekrs.neuro_simulation.utils.Cloner;

public class Eyes extends Sensor{
    private static final int SENSOR_NEURON_NUM = 4;
    public SensorNeuron[] neurons = new SensorNeuron[SENSOR_NEURON_NUM];

    public float limit;

    public Eyes(float limit) {
        this.limit = limit;
        for (int i = 0; i < SENSOR_NEURON_NUM; ++i)
            neurons[i] = new SensorNeuron();
    }
    @Override
    public int getNeuronNumber() {
        return SENSOR_NEURON_NUM;
    }

    @Override
    public SensorNeuron[] getSensorNeurons() {
        return neurons;
    }

    @Override
    public Component clone() {
        Eyes eyes = (Eyes) super.clone();
        eyes.neurons = (SensorNeuron[]) Cloner.copyArray(neurons);
        return eyes;
    }
}
