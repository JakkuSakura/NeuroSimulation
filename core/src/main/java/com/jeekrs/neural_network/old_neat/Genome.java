package com.jeekrs.neural_network.old_neat;

import com.jeekrs.neural_network.utils.Sets;

import java.util.Set;
import java.util.TreeMap;

public class Genome {
    public NeatCore core;
    public TreeMap<Integer, NodeGene> nodes;
    public TreeMap<Integer, Gene> connection_genes;

    public Genome(NeatCore core) {
        nodes = new TreeMap<>();
        connection_genes = new TreeMap<>();
        this.core = core;
    }

    public Genome(boolean empty_true) {
        // do nothing
    }

    public void addNode(int id, NodeGene.Type type) {
        nodes.put(id, new NodeGene() {{
            node_type = type;
            node_id = id;
        }});
    }
    public void addGene(Gene gene)
    {
        connection_genes.put(gene.innovation_number, gene);
    }

    /**
     * we can measure the compatibility distance δ of different structures in NEAT as a simple linear combination of the
     * number of excess E and disjoint D genes, as well as the average weight differences of matching genes W,
     * including disabled genes.
     * δ =c1E/N + c2D/N + c3W/N
     *
     * @param lhs
     * @param rhs
     * @return
     */
    public static float compatibilityDistance(Genome lhs, Genome rhs, float c1, float c2, float c3) {
        float n = Math.max(lhs.connection_genes.size(), rhs.connection_genes.size());
        if (n <= 20)
            n = 1;

        Set<Integer> inter = Sets.intersection(lhs.connection_genes.keySet(), rhs.connection_genes.keySet());
        float w = (float) inter.stream().mapToDouble(e -> Math.abs(lhs.connection_genes.get(e).weight - rhs.connection_genes.get(e).weight)).sum();
        return c1 * disjointConnectionCount(lhs, rhs) / n + c2 * excessConnectionCount(lhs, rhs) / n + c3 * w / n;
    }

    public static Genome crossover(Genome lhs, Genome rhs, float preference) {

        Genome genome = new Genome(true);
        genome.crossoverConnections(lhs, rhs, preference);
        genome.generateNodeGenes(lhs, rhs);
        assert lhs.core == rhs.core;
        genome.core = lhs.core;
        return genome;
    }


    /**
     * Call it after generateConnectionGenes()
     *
     * @param lhs
     * @param rhs
     * @return
     */
    private TreeMap<Integer, NodeGene> generateNodeGenes(Genome lhs, Genome rhs) {
        TreeMap<Integer, NodeGene> all_nodes = new TreeMap<>();
        all_nodes.putAll(lhs.nodes);
        all_nodes.putAll(rhs.nodes);
        Set<Integer> intersection = lhs.nodes.keySet();
        intersection.retainAll(rhs.nodes.keySet());

        intersection.forEach(e -> nodes.put(e, all_nodes.get(e)));

        connection_genes.forEach((k, v) -> {
            nodes.put(v.from, all_nodes.get(v.from));
            nodes.put(v.to, all_nodes.get(v.to));
        });
        return nodes;

    }

    /**
     * Crossover the connection genes of two parents.
     *
     * @param lhs
     * @param rhs
     * @param preference
     * @return
     */
    private TreeMap<Integer, Gene> crossoverConnections(Genome lhs, Genome rhs, float preference) {
        TreeMap<Integer, Gene> genes = new TreeMap<>();
        Set<Integer> all_cg = Sets.union(lhs.connection_genes.keySet(), rhs.connection_genes.keySet());
        all_cg.forEach(e -> {
            if (lhs.connection_genes.containsKey(e) && rhs.connection_genes.containsKey(e)) {
                genes.put(e, Math.random() < 0.5 ? lhs.connection_genes.get(e) : rhs.connection_genes.get(e));
            } else if (lhs.connection_genes.containsKey(e)) {
                if (preference > 0 || preference == 0 && Math.random() < 0.5) genes.put(e, lhs.connection_genes.get(e));
            } else // if (rhs.connection_genes.containsKey(e))
            {
                if (preference < 0 || preference == 0 && Math.random() < 0.5) genes.put(e, rhs.connection_genes.get(e));
            }
        });
        connection_genes = genes;
        return genes;
    }

    public static int disjointConnectionCount(Genome lhs, Genome rhs) {
        // make sure that lhs has the least innovation number
        if (lhs.connection_genes.floorKey(Integer.MAX_VALUE) > rhs.connection_genes.floorKey(Integer.MAX_VALUE))
            return disjointConnectionCount(rhs, lhs);
        int least_inno = lhs.connection_genes.floorKey(Integer.MAX_VALUE);
        Set<Integer> all_cg = Sets.union(lhs.connection_genes.keySet(), rhs.connection_genes.keySet());
        Set<Integer> inter = Sets.intersection(lhs.connection_genes.keySet(), rhs.connection_genes.keySet());
        return (int) all_cg.stream().filter(e -> e <= least_inno).count() - inter.size();
    }

    public static int excessConnectionCount(Genome lhs, Genome rhs) {
        // make sure that lhs has the least largest innovation number
        if (lhs.connection_genes.floorKey(Integer.MAX_VALUE) > rhs.connection_genes.floorKey(Integer.MAX_VALUE))
            return excessConnectionCount(rhs, lhs);
        int least_inno = lhs.connection_genes.floorKey(Integer.MAX_VALUE);
        Set<Integer> all_cg = Sets.union(lhs.connection_genes.keySet(), rhs.connection_genes.keySet());

        return (int) all_cg.stream().filter(e -> e > least_inno).count();
    }
    public void mutate() {

    }

    public int getHighestNode() {
        return nodes.ceilingKey(Integer.MAX_VALUE);
    }
}
