package com.jeekrs.neuro_simulation.processors;

import com.jeekrs.neuro_simulation.entities.livings.Living;
import com.jeekrs.neuro_simulation.neuron_net.NeuroNet;
import com.jeekrs.neuro_simulation.utils.Package;
import com.jeekrs.neuro_simulation.utils.RandomUtil;


public class NeuroProcessor implements Processor {
    private Living living;
    private int depth;
    private int width;
    private int input_num;
    private int output_num;
    private NeuroNet neuroNet = new NeuroNet();

    public NeuroNet getNeuroNet() {
        return neuroNet;
    }

    public NeuroProcessor(Living living, int depth, int width) {
        this.living = living;
        this.depth = depth;
        this.width = width;
    }

    public void shuffle(int times) {
        float[][] X = new float[times][input_num];
        float[][] Y = new float[times][output_num];
        for (int i = 0; i < times; i++) {
            for (int j = 0; j < input_num; ++j) X[i][j] = RandomUtil.nextFloat();
            for (int j = 0; j < output_num; ++j) Y[i][j] = RandomUtil.nextFloat();
        }
        neuroNet.train(X, Y);
    }

    public void adjust(float x) {
        for (int i = 0; i < neuroNet.weight.length; i++) {
            for (int j = 0; j < neuroNet.weight[i].length; j++) {
                for (int k = 0; k < neuroNet.weight[i][j].length; k++) {
                    neuroNet.weight[i][j][k] += RandomUtil.nextFloat(-x, x);
                }
            }
        }
    }
    @Override
    public Package process(Package p) {

        float[] predict = neuroNet.predict(p.getRawArray());
        return new Package(predict);
    }

    @Override
    public Processor clone() {
        try {
            NeuroProcessor clone = (NeuroProcessor) super.clone();
            clone.neuroNet = clone.neuroNet.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void init() {
        input_num = living.sensoryPackageLength();
        output_num = living.effectorPackageLength();
        int[] lens = new int[depth + 2];
        lens[0] = input_num;
        for (int i = 1; i < lens.length - 1; i++) {
            lens[i] = width;
        }
        lens[lens.length - 1] = output_num;
        neuroNet.init(lens);
    }
}
