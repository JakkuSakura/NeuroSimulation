package com.jeekrs.neuro_simulation.components.data;

import com.jeekrs.neural_network.dense.*;
import com.jeekrs.neuro_simulation.components.Component;
import com.jeekrs.neuro_simulation.components.effectors.Effector;
import com.jeekrs.neuro_simulation.components.sensors.Sensor;
import com.jeekrs.neuro_simulation.utils.RandomUtil;

import java.util.Arrays;

public class DenseLayersAdapter extends NeuralNetworkAdapter {

    public DenseLayersAdapter(Sensor[] sensors, Effector[] effectors, int[] shape) {
        if (sensors != null && effectors != null && shape != null) {
            sensor_input_num = Arrays.stream(sensors).mapToInt(Sensor::getInputNumber).sum();
            effector_output_num = Arrays.stream(effectors).mapToInt(Effector::getOutputNumber).sum();
            setNetwork(new DenseLayers(sensor_input_num, effector_output_num, shape));
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
    public void activate() {
        getNetwork().activate();
    }

    @Override
    protected DenseLayers getNetwork() {
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
