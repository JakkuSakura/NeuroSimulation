package com.jeekrs.neural_network;

import com.jeekrs.neuro_simulation.interfaces.PublicClonable;

public abstract class NeuralNetwork implements PublicClonable<NeuralNetwork> {
    public abstract void inputs(float[] inputs);
    public abstract void activate();
    public abstract float[] outputs();
    public NeuralNetwork clone() {
        try {
            return (NeuralNetwork) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
