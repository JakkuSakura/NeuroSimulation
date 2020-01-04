package com.jeekrs.neuro_simulation.components.data;

import com.jeekrs.neuro_simulation.components.Component;
import com.jeekrs.neuro_simulation.components.effectors.Effector;
import com.jeekrs.neural_network.dense.EffectorNeuron;
import com.jeekrs.neural_network.dense.SensorNeuron;
import com.jeekrs.neuro_simulation.components.sensors.Sensor;

public class NeuralNetwork extends Component {
    public com.jeekrs.neural_network.dense.NeuralNetwork network;

    public NeuralNetwork(Sensor[] sn, Effector[] ef, int[] shape) {

        if (sn != null && ef != null && shape != null) {
            int sensor_neuron_num = 0;
            for (Sensor s : sn)
                sensor_neuron_num += s.getNeuronNumber();
            SensorNeuron[] sensor_neurons = new SensorNeuron[sensor_neuron_num];
            int index = 0;
            for (Sensor s : sn) {
                System.arraycopy(s.getSensorNeurons(), 0, sensor_neurons, index, s.getNeuronNumber());
                index += s.getNeuronNumber();
            }
            int effector_neuron_num = 0;
            for (Effector e : ef)
                effector_neuron_num += e.getNeuronNumber();
            EffectorNeuron[] effector_neurons = new EffectorNeuron[effector_neuron_num];
            index = 0;
            for (Effector e : ef) {
                System.arraycopy(e.getEffectorNeurons(), 0, effector_neurons, index, e.getNeuronNumber());
                index += e.getNeuronNumber();
            }
            network = new com.jeekrs.neural_network.dense.NeuralNetwork(sensor_neurons, effector_neurons, shape);
        } else {
            throw new IllegalStateException("You have to set up sensors, effectors, and shape before constructing the neural network");
        }
    }

    @Override
    public Component clone() {
        NeuralNetwork clone = (NeuralNetwork) super.clone();
        clone.network = network.clone();
        return clone;
    }
}
