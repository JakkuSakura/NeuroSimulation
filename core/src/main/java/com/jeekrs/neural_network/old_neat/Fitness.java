package com.jeekrs.neural_network.old_neat;

import java.util.Collection;

public class Fitness {
    public static float adjustedFitness(float fitness, Genome current, Collection<Genome> population, float threshold, float c1, float c2, float c3)
    {
        float n = population.stream().filter(e -> Genome.compatibilityDistance(current, e, c1, c2, c3) <= threshold).count();
        return fitness / n;
    }
}
