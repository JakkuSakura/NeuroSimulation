package com.jeekrs.neuro_simulation.components.data;

import com.jeekrs.neural_network.dense.*;
import com.jeekrs.neuro_simulation.components.Component;
import com.jeekrs.neuro_simulation.components.effectors.Effector;
import com.jeekrs.neuro_simulation.components.sensors.Sensor;
import com.jeekrs.neuro_simulation.utils.RandomUtil;

public class DenseLayersAdapter extends NeuralNetworkAdapter {

    public DenseLayersAdapter(Sensor[] sn, Effector[] ef, int[] shape) {

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
            setNetwork(new DenseLayers(sensor_neurons, effector_neurons, shape));
            initiate();
        } else {
            throw new IllegalStateException("You have to set up sensors, effectors, and shape before constructing the neural network");
        }
    }

    public void initiate() {
        int n = 30;
        while (n-- > 0) {
            Neuron[][] nodes = getNetwork().getNodes();
            int i = RandomUtil.nextInt(0, nodes.length);
            int j = RandomUtil.nextInt(0, nodes.length);
            if (i > j)
                i = i ^ j ^ (j = i);
            else if (i == j) {
                ++n;
                continue;
            }
            int a = RandomUtil.nextInt(0, nodes[i].length);
            int b = RandomUtil.nextInt(0, nodes[j].length);
//            System.out.format("(%d, %d) -> (%d, %d)\n", i, a, j ,b);
            getNetwork().makeLink(i, a, j, b, 2 * RandomUtil.nextFloat() - 1);

        }
    }

    @Override
    public DenseLayers getNetwork() {
        return (DenseLayers) super.getNetwork();
    }

    @Override
    public void mutate() {
        for (Link link : getNetwork().getLinks()) {
            link.weight *= RandomUtil.nextFloat(0.8f, 1.2f);
        }
    }

    @Override
    public Component clone() {
        return super.clone();
    }

}
