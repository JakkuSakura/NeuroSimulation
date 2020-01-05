//package com.jeekrs.neural_network.neat;
//
//import com.jeekrs.neuro_simulation.utils.RandomUtil;
//
//import java.util.*;
//
///**
// * There are three types of mutations.
// *
// * 		1. Add a new node. The new input weight to that node will be 1.
// * 		   The output from the new node will be set to the old connection's weight value.
// *
// * 		2. Add a new link with a random weight between two existing nodes.
// *
// * 		3. The weights of an existing connection are changed.
// *
// */
//public class Mutation {
//
//    private final Genome genome;
//
//    public Mutation(Genome genome) {
//        this.genome = genome;
//    }
//
//    public Gene random(Genome genome) {
//        int random_index = RandomUtil.nextInt(0, genome.connection_genes.size());
//        Iterator<Gene> iterator = genome.connection_genes.values().iterator();
//        for (int i = 0; i < random_index -1; ++i)
//            iterator.next();
//        return iterator.next();
//    }
//    public void mutate() {
//
//        /**
//         * 1. Add a new node. The new input weight to that node will be 1.
//         * 	  The output from the new node will be set to the old connection's weight value.
//         */
//        if (RandomUtil.success(this.genome.core.settings.MUTATION_NEW_NODE_CHANCE)) {
//
//            Gene randomGene = random(this.genome);
//            randomGene.isEnabled = false;
//
//            // two new genes
//            int from = randomGene.from;
//            int to = randomGene.to;
//
//            this.genome.core.innovation.getInnovationNumber();
//
//            int newNodeId = this.genome.getHighestNode() + 1;
//            this.genome.addGene(new Gene(this.genome.core.innovation.getInnovationNumber(), from, newNodeId, 1f, true));
//            this.genome.addGene(new Gene(this.genome.core.innovation.getInnovationNumber(), newNodeId, to, randomGene.weight, true));
//        }
//
//        /**
//         * 2. Add a new link with a random weight between two existing nodes.
//         *    Start by finding two yet unconnected nodes. One of them must be a hidden node.
//         */
//        if (RandomUtil.success(this.genome.core.settings.MUTATION_NEW_CONNECTION_CHANCE)) {
//            // todo
//            try {
//                /**
//                 * Instead of looping through all possible connections and choosing one from
//                 * the obtained list, we pick a random connection and hope it doesn't exist
//                 * yet. We do this because once the network gets bigger, looping through all
//                 * possible connections would be a very intensive task.
//                 */
//                Collection<? extends Connection> currentConnections = this.genome.connection_genes;
//
//                int attempts = 0;
//
//                Connection maybeNew = null;
//                do {
//                    {
//                        if (attempts++ > 40)
//                            throw new MutationFailedException("New connection could not be created after 40 attempts.");
//                    }
//
//                    int from = Random.random(this.genome.getNodes(true, true, false));
//
//                    List<Integer> leftOver = this.genome.getNodes(false, true, true);
//                    leftOver.remove((Object) from); // cast to Object, otherwise the wrong method remove(int index); will be called
//
//                    if (leftOver.isEmpty())
//                        continue;
//
//                    int to = Random.random(leftOver);
//
//                    maybeNew = new Connection(from, to);
//                } while (maybeNew == null || maybeNew.getFrom() == maybeNew.getTo() || currentConnections.contains(maybeNew) || isRecurrent(maybeNew));
//
//                // add it to the network
//                genome.addGene(new Gene(this.genome.core.innovation.getInnovationNumber(), maybeNew.getFrom(), maybeNew.getTo(), Random.random(-1, 1), true), null, null);
//            } catch (MutationFailedException e) {
//                // System.out.println("Mutation Failed: " + e.getMessage());
//            }
//        }
//
//        /**
//         * 3. The weights of an existing connection are changed.
//         */
//        if (Random.success(this.genome.core.getSetting(Setting.MUTATION_WEIGHT_CHANCE))) {
//            if (Random.success(this.genome.core.getSetting(Setting.MUTATION_WEIGHT_RANDOM_CHANCE))) {
//                // assign a random new value
//                for (Gene gene : this.genome.getGenes()) {
//                    double range = this.genome.core.getSetting(Setting.MUTATION_WEIGHT_CHANCE_RANDOM_RANGE);
//                    gene.setWeight(Random.random(-range, range));
//                }
//            } else {
//                // uniformly perturb
//                for (Gene gene : this.genome.getGenes()) {
//                    double disturbance = this.genome.core.getSetting(Setting.MUTATION_WEIGHT_MAX_DISTURBANCE);
//                    double uniform = Random.random(-disturbance, disturbance);
//                    gene.setWeight(gene.getWeight() + uniform);
//                }
//            }
//        }
//    }
//
//    public boolean isRecurrent(Connection with) {
//        Genome tmpGenome = this.genome.clone(); // clone so we can change its genes without actually affecting the original genome
//
//        if (with != null) {
//            Gene gene = new Gene(tmpGenome.getHighestInnovationNumber() + 1, with.getFrom(), with.getTo(), 0, true);
//            tmpGenome.addGene(gene, null, null);
//        }
//
//        boolean recc = false;upd
//        for (int hiddenNode : tmpGenome.getHiddenNodes()) {
//            if (isRecurrent(new ArrayList<>(), tmpGenome, hiddenNode)) {
//                recc = true;
//            }
//        }
//        return recc;
//    }
//
//    private boolean isRecurrent(List<Integer> path, Genome genome, int node) {
//        if (path.contains(node)) {
//            /**
//             * We've been here before, we're in an infinite loop.
//             */
//            return true;
//        }
//        path.add(node);
//
//        boolean recc = false;
//        for (int from : this.getInputs(genome, node)) {
//            if (!genome.isInputNode(from)) {
//                if (this.isRecurrent(path, genome, from)) {
//                    recc = true;
//                }
//            }
//        }
//        return recc;
//    }
//
//    private List<Integer> getInputs(Genome genome, int node) {
//        List<Integer> froms = new ArrayList<>();
//        for (Gene gene : genome.getGenes()) {
//            if (gene.getTo() == node) {
//                froms.add(gene.getFrom());
//            }
//        }
//        return froms;
//    }
//}