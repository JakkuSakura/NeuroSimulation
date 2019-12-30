package com.jeekrs.neuro_simulation.neuron_net;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;

public class NeuralNetwork {
    public ArrayList<Neuron>[] nodes;
    public NeuralNetwork(Sensor[] sensors, Effector[] effectors, int[] middle_shape) {
        int layer_num = middle_shape.length;
        //noinspection unchecked
        nodes = (ArrayList<Neuron>[])new ArrayList[layer_num + 2];
        Collections.addAll(nodes[0], sensors);
        Collections.addAll(nodes[layer_num + 1], effectors);
        for(int i = 1; i <= layer_num; ++i) {
            nodes[i].add(new HiddenNeuron());
        }
    }
    public void activate() {
        ArrayDeque<Neuron> queue = new ArrayDeque<>();
        queue.addAll(nodes[0]);
        while (queue.size() > 0)
        {
            Neuron n = queue.pop();
            float output = n.calculate();
            // todo

        }
    }
}
