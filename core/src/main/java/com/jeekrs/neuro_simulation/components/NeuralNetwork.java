package com.jeekrs.neuro_simulation.components;

import com.jeekrs.neuro_simulation.effectors.Effector;
import com.jeekrs.neuro_simulation.neuron_net.EffectorNeuron;
import com.jeekrs.neuro_simulation.neuron_net.SensorNeuron;
import com.jeekrs.neuro_simulation.sensors.Sensor;

import java.util.ArrayList;
import java.util.Arrays;

public class NeuralNetwork extends Component {
    public com.jeekrs.neuro_simulation.neuron_net.NeuralNetwork network;
    public ArrayList<Sensor> sensors;
    public ArrayList<Effector> effectors;
    private int[] shape;

    public NeuralNetwork(ArrayList<Sensor> s, ArrayList<Effector> e) {
        sensors = s;
        effectors = e;
    }

    public NeuralNetwork() {
        sensors = new ArrayList<>();
        effectors = new ArrayList<>();
    }

    void construct() {
        if (sensors != null && effectors != null) {
            int sensor_neuron_num = 0;
            for (Sensor s : sensors)
                sensor_neuron_num += s.getNeuronNumber();
            SensorNeuron[] sensor_neurons = new SensorNeuron[sensor_neuron_num];
            int index = 0;
            for (Sensor s : sensors) {
                System.arraycopy(s.getSensorNeurons(), 0, sensor_neurons, index, s.getNeuronNumber());
                index += s.getNeuronNumber();
            }
            int effector_neuron_num = 0;
            for (Effector e : effectors)
                effector_neuron_num += e.getNeuronNumber();
            EffectorNeuron[] effector_neurons = new EffectorNeuron[effector_neuron_num];
            index = 0;
            for (Effector e : effectors) {
                System.arraycopy(e.getEffectorNeurons(), 0, effector_neurons, index, e.getNeuronNumber());
                index += e.getNeuronNumber();
            }
            network = new com.jeekrs.neuro_simulation.neuron_net.NeuralNetwork(sensor_neurons, effector_neurons, shape);
        } else {
            throw new IllegalStateException("You have to set up sensors, effectors, and shape before constructing the neural network");
        }
    }
}
