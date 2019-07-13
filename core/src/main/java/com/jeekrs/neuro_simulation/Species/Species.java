package com.jeekrs.neuro_simulation.Species;

public class Species {

    public static Species getSpecies(String text) {
        if (text.equals("Ants"))
            return new Ants();
        return null;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
