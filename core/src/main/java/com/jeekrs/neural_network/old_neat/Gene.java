package com.jeekrs.neural_network.old_neat;

public class Gene implements Cloneable, Comparable<Gene>{
    public int from;
    public int to;
    public int innovation_number;
    public float weight;
    public boolean isEnabled;

    public Gene(int innovation_number, int from, int to, float weight, boolean isEnabled)
    {

        this.from = from;
        this.to = to;
        this.innovation_number = innovation_number;
        this.weight = weight;
        this.isEnabled = isEnabled;
    }
    @Override
    public Gene clone(){
        try {
            return (Gene) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public int compareTo(Gene gene) {
        return Integer.compare(innovation_number, gene.innovation_number);
    }
}
