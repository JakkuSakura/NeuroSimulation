package com.jeekrs.neural_network.dense;

import com.jeekrs.neural_network.NeuralNetwork;
import com.jeekrs.neuro_simulation.utils.Cloner;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class DenseLayers extends NeuralNetwork {
    private Neuron[][] nodes;
    private ArrayList<Link> links = new ArrayList<>();
    public DenseLayers(int input_size, int output_size, int[] middle_shape) {
        int layer_num = middle_shape.length;
        nodes =  new Neuron[layer_num + 2][];

        nodes[0] = new Neuron[input_size];
        for (int i = 0; i < input_size; ++i)
            nodes[0][i] = new SensorNeuron();

        nodes[layer_num + 1] = new Neuron[output_size];
        for (int i = 0; i < output_size; ++i)
            nodes[layer_num + 1][i] = new EffectorNeuron();

        for (int i = 1; i <= layer_num; ++i) {
            nodes[i] = new Neuron[middle_shape[i-1]];
            for (int j = 0; j < middle_shape[i - 1]; ++j)
                nodes[i][j] = new HiddenNeuron();
        }
    }

    public DenseLayers() {

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

    @Override
    public void inputs(float[] inputs) {
        for (int i = 0; i < inputs.length; i++) {
            nodes[0][i].update(i);
        }
    }


    @Override
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

    @Override
    public float[] outputs() {
        Neuron[] output_nodes = nodes[nodes.length - 1];
        float[] outputs = new float[output_nodes.length];
        for (int i = 0; i < output_nodes.length; i++) {
            outputs[i] = output_nodes[i].value();
        }
        return outputs;
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
        int[] shape = new int[1];
        shape[0] = 5;
        DenseLayers neuralNetwork = new DenseLayers(1, 1, shape);
        neuralNetwork.makeLink(0,0,1,1,1);
        neuralNetwork.makeLink(1,1,2,0, 1);


        neuralNetwork.activate();
        for (Neuron l : neuralNetwork.nodes[2]) {
            System.out.println(l.calculate());
        }
    }

    @Override
    public DenseLayers clone() {
        DenseLayers clone = (DenseLayers) super.clone();
        clone.nodes = Cloner.copyArray(nodes);
        clone.links = Cloner.deepCopy(links);
        return clone;
    }

    public Neuron[][] getNodes() {
        return nodes;
    }

    public void setNodes(Neuron[][] nodes) {
        this.nodes = nodes;
    }

    public ArrayList<Link> getLinks() {
        return links;
    }

    public void setLinks(ArrayList<Link> links) {
        this.links = links;
    }
}
