package com.jeekrs.neuro_simulation.entities.nest;

import com.jeekrs.neuro_simulation.entities.livings.AntFighter;

public class AntFighterNest extends Nest {
    public AntFighterNest() {
        setPrototype(new AntFighter());
    }
}
