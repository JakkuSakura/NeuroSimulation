package com.jeekrs.neuro_simulation.entities.livings;

import com.jeekrs.neuro_simulation.processors.NeuroProcessor;
import com.jeekrs.neuro_simulation.processors.effectors.Effector;
import com.jeekrs.neuro_simulation.processors.sensories.Sensory;
import com.jeekrs.neuro_simulation.utils.Cloner;
import com.jeekrs.neuro_simulation.utils.Package;

import java.util.ArrayList;

public class NeuralLiving extends Living {

    private NeuroProcessor processor;
    private ArrayList<Sensory> sensories = new ArrayList<>();
    private ArrayList<Effector> effects = new ArrayList<>();

    protected void addSensory(Sensory s) {
        sensories.add(s);
    }

    protected void addEffector(Effector e) {
        effects.add(e);
    }

    public int sensoryPackageLength() {
        return detect().vals.size();
    }

    public int effectorPackageLength() {
        return effects.stream().mapToInt(Effector::neededLength).sum();
    }

    public NeuroProcessor getProcessor() {
        return processor;
    }

    public Package detect() {
        Package p = new Package();
        sensories.forEach(e -> p.append(e.detect(this)));
        return p;
    }

    public Package process(Package p) {
        return processor.process(p);
    }

    public void effect(Package p) {
        for (int i = 0, j = 0; i < effects.size(); i++) {
            effects.get(i).effect(p.slice(j, effects.get(i).neededLength()), this);
        }
    }

    public void setProcessor(NeuroProcessor processor) {
        this.processor = processor;
    }

    @Override
    public Living clone() {
        NeuralLiving living = (NeuralLiving) super.clone();
        living.processor = processor.clone();
        living.effects = Cloner.deepCopy(effects);
        living.sensories = Cloner.deepCopy(sensories);
        return living;
    }

    @Override
    public Living breed() {
        NeuralLiving living = (NeuralLiving) super.breed();
        if (living.getProcessor() != null) {
            living.getProcessor().adjust(0.2f);
        }
        return living;
    }
}
