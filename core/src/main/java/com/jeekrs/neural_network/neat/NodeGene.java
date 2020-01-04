package com.jeekrs.neural_network.neat;

public class NodeGene implements Comparable<NodeGene> {
    @Override
    public int compareTo(NodeGene nodeGene) {
        return Integer.compare(node_id, nodeGene.node_id);
    }

    public enum Type {
        INPUT, HIDDEN, OUTPUT
    }
    public Type node_type;
    public int node_id;
}
