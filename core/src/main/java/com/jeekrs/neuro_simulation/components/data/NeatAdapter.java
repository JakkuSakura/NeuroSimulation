package com.jeekrs.neuro_simulation.components.data;

import com.jeekrs.neural_network.neat.NEAT;
import com.jeekrs.neuro_simulation.components.effectors.Effector;
import com.jeekrs.neuro_simulation.components.sensors.Sensor;

import java.util.Arrays;


public class NeatAdapter extends NeuralNetworkAdapter {

    public NeatAdapter(Sensor[] sensors, Effector[] effectors, int mutate_count) {
        if (sensors != null && effectors != null) {
            sensor_input_num = Arrays.stream(sensors).mapToInt(Sensor::getInputNumber).sum();
            effector_output_num = Arrays.stream(effectors).mapToInt(Effector::getOutputNumber).sum();
            setNetwork(new NEAT(sensor_input_num, effector_output_num));
            for (int i = 0; i < mutate_count; i++) {
                mutate();
            }
        } else {
            throw new IllegalStateException("You have to set up sensors, effectors, and shape before constructing the neural network");
        }
    }


    @Override
    public void mutate() {
        getNetwork().mutate();
    }


    @Override
    public void activate() {
        getNetwork().activate();

    }

    @Override
    protected NEAT getNetwork() {
        return (NEAT) super.getNetwork();
    }


}
