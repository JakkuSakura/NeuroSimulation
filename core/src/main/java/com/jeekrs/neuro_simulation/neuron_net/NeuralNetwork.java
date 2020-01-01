package com.jeekrs.neuro_simulation.neuron_net;

import java.util.*;

public class NeuralNetwork {
    public Neuron[][] nodes;

    public NeuralNetwork(SensorNeuron[] sensorNeurons, EffectorNeuron[] effectorNeurons, int[] middle_shape) {
        int layer_num = middle_shape.length;
        nodes =  new Neuron[layer_num + 2][];

        nodes[0] = sensorNeurons;
        nodes[layer_num + 1] = effectorNeurons;
        for (int i = 1; i <= layer_num; ++i) {
            nodes[i] = new Neuron[middle_shape[i-1]];
            for (int j = 0; j < middle_shape[i - 1]; ++j)
                nodes[i][j] = new HiddenNeuron();
        }
    }

    public void activate() {
        ArrayDeque<Neuron> queue = new ArrayDeque<>();
        HashMap<Neuron, Integer> ranks = new HashMap<>();
        preprocess(queue, ranks);

        while (queue.size() > 0) {
            Neuron n = queue.pop();
            if (ranks.get(n) > 0) {
                queue.add(n);
                continue;
            }

            float v = n.calculate();
            for (Link i : n.output) {
                i.to.update(v * i.weight);
                ranks.put(i.to, ranks.get(i.to) - 1);
            }
        }

    }

    private void preprocess(ArrayDeque<Neuron> queue2, HashMap<Neuron, Integer> ranks) {
        ArrayDeque<Neuron> queue = new ArrayDeque<>();
        for (Neuron[] node : nodes) Collections.addAll(queue, node);

        while (queue.size() > 0) {
            Neuron n = queue.pop();
            n.initiate();
            queue2.add(n);
            ranks.put(n, n.input.size());
        }
    }

    public static void main(String[] args) {
        ConstantSensor sensor = new ConstantSensor(1);
        SensorNeuron[] sensorNeurons = new SensorNeuron[1];
        sensorNeurons[0] = sensor.getNeuron();
        EffectorNeuron[] effectorNeuron = new EffectorNeuron[1];
        effectorNeuron[0] = new EffectorNeuron();
        int[] shape = new int[1];
        shape[0] = 5;
        NeuralNetwork neuralNetwork = new NeuralNetwork(sensorNeurons, effectorNeuron, shape);
        Link.makeLink(neuralNetwork.nodes[0][0], neuralNetwork.nodes[1][1], 1);
        Link.makeLink(neuralNetwork.nodes[1][1], neuralNetwork.nodes[2][0], 1);

        neuralNetwork.activate();
        for (Neuron l : neuralNetwork.nodes[2]) {
            System.out.println(l.calculate());
        }
    }
}
