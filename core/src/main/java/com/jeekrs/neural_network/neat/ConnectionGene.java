package com.jeekrs.neural_network.neat;

public class ConnectionGene implements Cloneable, Comparable<ConnectionGene>{
    public int in, out, innovation_number;
    public boolean enabled;
    public float weight;

    @Override
    public ConnectionGene clone(){
        try {
            return (ConnectionGene) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public int compareTo(ConnectionGene connectionGene) {
        return Integer.compare(innovation_number, connectionGene.innovation_number);
    }
}
