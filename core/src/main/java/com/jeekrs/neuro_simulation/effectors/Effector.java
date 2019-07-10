package com.jeekrs.neuro_simulation.effectors;
import com.jeekrs.neuro_simulation.utils.Package;
public interface Effector {
    void effect(Package p);
    int neededLength();
}
