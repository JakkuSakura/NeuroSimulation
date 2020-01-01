package com.jeekrs.neuro_simulation.neural_network;

public class ConstantSensor {
    private Neuron neuron = new Neuron();
    private float value;

    public ConstantSensor(float value) {
        this.value = value;
    }

    public Neuron getNeuron() {
        return neuron;
    }

    public class Neuron extends SensorNeuron {

        @Override
        public float calculate() {
            return value;
        }
    }
}
