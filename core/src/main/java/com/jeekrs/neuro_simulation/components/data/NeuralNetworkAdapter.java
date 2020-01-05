package com.jeekrs.neuro_simulation.components.data;

import com.jeekrs.neuro_simulation.components.Component;

public abstract class NeuralNetworkAdapter extends Component {
    private com.jeekrs.neural_network.NeuralNetwork network;

//    public abstract void initiate();
    public abstract void mutate();
    @Override
    public Component clone() {
        NeuralNetworkAdapter clone = (NeuralNetworkAdapter) super.clone();
        clone.network = network.clone();
        return clone;
    }

    public com.jeekrs.neural_network.NeuralNetwork getNetwork() {
        return network;
    }

    public void setNetwork(com.jeekrs.neural_network.NeuralNetwork network) {
        this.network = network;
    }

    public void activate() {
        getNetwork().activate();
    }
}
