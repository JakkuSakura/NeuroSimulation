package com.jeekrs.neuro_simulation.neural_network;

import com.jeekrs.neuro_simulation.interfaces.PublicClonable;
import com.jeekrs.neuro_simulation.utils.Cloner;

import java.util.*;

public class NeuralNetwork implements PublicClonable<NeuralNetwork> {
    public Neuron[][] nodes;
    public ArrayList<Link> links = new ArrayList<>();
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

    public NeuralNetwork() {

    }

    public Link makeLink(int i1, int j1, int i2, int j2, float weight) {
        Link link = new Link();
        link.form = i1 << 16 | j1;
        link.to = i2 << 16 | j2;
        link.weight = weight;
        links.add(link);
        int index = links.size() - 1;
        nodes[i1][j1].output.add(index);
        nodes[i2][j2].input.add(index);
        return link;
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
            for (int i : n.output) {
                Link link = links.get(i);
                Neuron node = locateNode(link.to);

                node.update(v * link.weight);
                ranks.put(node, ranks.get(node) - 1);
            }
        }

    }

    private Neuron locateNode(int index) {
        int i = index >> 16;
        int j = index ^ (i << 16);
        return nodes[i][j];
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
    public void printValues() {
        for (Neuron[] layer : nodes) {
            for (Neuron neuron : layer) {
                System.out.print(neuron);
                System.out.print(" ");
            }
            System.out.println();
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
        neuralNetwork.makeLink(0,0,1,1,1);
        neuralNetwork.makeLink(1,1,2,0, 1);


        neuralNetwork.activate();
        for (Neuron l : neuralNetwork.nodes[2]) {
            System.out.println(l.calculate());
        }
    }

    @Override
    public NeuralNetwork clone() {
        NeuralNetwork clone = null;
        try {
            clone = (NeuralNetwork) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        clone.nodes = Cloner.copyArray(nodes);
        clone.links = Cloner.deepCopy(links);
        return clone;
    }
}
