package com.jeekrs.neuro_simulation.components.data;

import com.jeekrs.neural_network.NeuralNetwork;
import com.jeekrs.neuro_simulation.components.Component;
import com.jeekrs.neuro_simulation.components.effectors.Effector;
import com.jeekrs.neuro_simulation.components.sensors.Sensor;

public abstract class NeuralNetworkAdapter extends Component {
    protected int sensor_input_num;
    protected int effector_output_num;
    private NeuralNetwork network;

    //    public abstract void initiate();
    public abstract void mutate();
    @Override
    public Component clone() {
        NeuralNetworkAdapter clone = (NeuralNetworkAdapter) super.clone();
        clone.network = network.clone();

        return clone;
    }

    protected NeuralNetwork getNetwork() {
        return network;
    }

    public void setNetwork(NeuralNetwork network) {
        this.network = network;
    }

    public void input(Sensor[] sensors) {
        float[] inputs = new float[sensor_input_num];
        int index = 0;
        for (Sensor sensor : sensors) {
            for (float i : sensor.getInputs()) {
                inputs[index] = i;
                ++index;
            }
        }

        getNetwork().inputs(inputs);
    }
    public abstract void activate();

    public void output(Effector[] effectors) {
        float[] outputs = getNetwork().outputs();
        int index = 0;
        for (Effector effector : effectors) {
            float[] effectorsOutputs = effector.getOutputs();
            for (int i = 0; i < effectorsOutputs.length; i++) {
                effectorsOutputs[i] = outputs[index];
                ++index;
            }
        }
    }
}
