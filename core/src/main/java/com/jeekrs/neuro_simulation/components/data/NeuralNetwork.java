package com.jeekrs.neuro_simulation.components.data;

import com.jeekrs.neuro_simulation.components.Component;
import com.jeekrs.neuro_simulation.components.effectors.Effector;
import com.jeekrs.neuro_simulation.neural_network.EffectorNeuron;
import com.jeekrs.neuro_simulation.neural_network.SensorNeuron;
import com.jeekrs.neuro_simulation.components.sensors.Sensor;

public class NeuralNetwork extends Component {
    public com.jeekrs.neuro_simulation.neural_network.NeuralNetwork network;
    public Sensor[] sensors;
    public Effector[] effectors;
    private int[] shape;

    public NeuralNetwork(Sensor[] s, Effector[] e, int[] shape) {
        sensors = s;
        effectors = e;
        this.shape = shape;
        construct();
    }


    void construct() {
        if (sensors != null && effectors != null && shape != null) {
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
            network = new com.jeekrs.neuro_simulation.neural_network.NeuralNetwork(sensor_neurons, effector_neurons, shape);
        } else {
            throw new IllegalStateException("You have to set up sensors, effectors, and shape before constructing the neural network");
        }
    }
}
