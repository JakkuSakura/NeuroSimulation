package com.jeekrs.neuro_simulation.components.data;

import com.jeekrs.neural_network.neat.NEAT;

// todo enable this
public class NeatAdapter extends NeuralNetworkAdapter {
    public NeatAdapter(int input_size, int output_size)
    {
        setNetwork(new NEAT(input_size, output_size));
    }

    @Override
    public void mutate() {
        ((NEAT)getNetwork()).mutate();
    }
}
