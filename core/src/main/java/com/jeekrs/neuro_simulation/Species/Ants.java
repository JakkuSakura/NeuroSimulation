package com.jeekrs.neuro_simulation.Species;

import com.jeekrs.neuro_simulation.entities.nest.AntFighterNest;
import com.jeekrs.neuro_simulation.entities.nest.Nest;

import java.util.ArrayList;

public class Ants extends Species {

    Ants() {
    }

    public ArrayList<Nest> getNests() {
        return nests;
    }

    public ArrayList<Nest> nests = new ArrayList<Nest>() {{
        add(new AntFighterNest());
    }};
}
