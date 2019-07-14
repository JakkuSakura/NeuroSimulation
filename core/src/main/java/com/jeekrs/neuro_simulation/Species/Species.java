package com.jeekrs.neuro_simulation.Species;

import com.jeekrs.neuro_simulation.entities.nest.Nest;

import java.util.ArrayList;

public abstract class Species {

    public static Species getSpecies(String text) {
        if (text.equals("Ants"))
            return new Ants();
        return null;
    }

    public abstract ArrayList<Nest> getNests();
    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
