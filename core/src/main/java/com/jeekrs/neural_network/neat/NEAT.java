package com.jeekrs.neural_network.neat;

import com.jeekrs.neural_network.NeuralNetwork;
import nl.sandergielisse.mythan.*;
import nl.sandergielisse.mythan.internal.EvolutionCore;
import nl.sandergielisse.mythan.internal.genes.Genome;

import java.util.Arrays;

public class NEAT extends NeuralNetwork {
    private EvolutionCore core;
    // todo maybe core can be shared
    private Genome genome;
    private double[] temp_inputs;
    private float[] temp_outputs;

    public NEAT(int input_size, int output_size) {
        core = new EvolutionCore(input_size, output_size, Math::tanh, null);

        core.setSetting(Setting.GENE_DISABLE_CHANCE, 0.75);
        core.setSetting(Setting.MUTATION_WEIGHT_CHANCE, 0.7);
        core.setSetting(Setting.MUTATION_WEIGHT_RANDOM_CHANCE, 0.10);
        core.setSetting(Setting.MUTATION_WEIGHT_MAX_DISTURBANCE, 0.1);

        core.setSetting(Setting.MUTATION_NEW_CONNECTION_CHANCE, 0.03);
        core.setSetting(Setting.MUTATION_NEW_NODE_CHANCE, 0.05);

        core.setSetting(Setting.DISTANCE_EXCESS_WEIGHT, 1.0);
        core.setSetting(Setting.DISTANCE_DISJOINT_WEIGHT, 1.0);
        core.setSetting(Setting.DISTANCE_WEIGHTS_WEIGHT, 0.4);

        core.setSetting(Setting.SPECIES_COMPATIBILTY_DISTANCE, 0.8); // the bigger the less species
        core.setSetting(Setting.MUTATION_WEIGHT_CHANCE_RANDOM_RANGE, 3);

        core.setSetting(Setting.GENERATION_ELIMINATION_PERCENTAGE, 0.85);
        core.setSetting(Setting.BREED_CROSS_CHANCE, 0.75);

        Integer[] ins = new Integer[input_size];
        Integer[] outs = new Integer[output_size];
        for (int i = 0; i < input_size; i++) {
            ins[i] = i;
        }
        for (int i = 0; i < output_size; i++) {
            outs[i] = input_size + i;
        }

        genome = new Genome(core, null, ins, outs);
        temp_inputs = new double[input_size];
        temp_outputs = new float[output_size];
    }

    @Override
    public void inputs(float[] input_data) {
        for (int i = 0; i < input_data.length; i++) {
            temp_inputs[i] = input_data[i];
        }
    }

    @Override
    public void activate() {
        double[] outs = genome.calculate(temp_inputs);
        for (int i = 0; i < outs.length; i++) {
            temp_outputs[i] = (float)outs[i];
        }
    }

    @Override
    public float[] outputs() {
        return temp_outputs;
    }

    public void mutate() {
        genome.mutate();
    }

    @Override
    public NEAT clone() {
        NEAT clone = (NEAT)super.clone();
        // clone.core can not to be changed
        clone.genome = this.genome.clone();
        clone.temp_inputs = temp_inputs.clone();
        clone.temp_outputs = temp_outputs.clone();
        return clone;
    }
}
