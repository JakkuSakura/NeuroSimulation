package com.jeekrs.neural_network.old_neat;

public class Innovation {
    private int innovation_number = 0;
    public int getInnovationNumber()
    {
        return ++innovation_number;
    }
    public int getLatestInnovationNumber() {
        return innovation_number;
    }
    public void resetInnovationNumber() {
        innovation_number = 0;
    }
}
