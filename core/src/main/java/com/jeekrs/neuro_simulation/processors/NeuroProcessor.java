package com.jeekrs.neuro_simulation.processors;

import com.jeekrs.neuro_simulation.NeuronNet.NeuroNet;
import com.jeekrs.neuro_simulation.entities.Living;
import com.jeekrs.neuro_simulation.utils.Package;


public class NeuroProcessor implements Processor {
    private Living living;
    private int depth;
    private int width;
    private int input_num;
    private int output_num;
    private NeuroNet neuroNet = new NeuroNet();

    public NeuroProcessor(Living living, int depth, int width) {
        this.living = living;
        this.depth = depth;
        this.width = width;
    }
    @Override
    public Package process(Package p) {

        double[] predict = neuroNet.predict(p.getRawArray());
        return new Package(predict);
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
