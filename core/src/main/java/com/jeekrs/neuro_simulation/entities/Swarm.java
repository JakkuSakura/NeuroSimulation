package com.jeekrs.neuro_simulation.entities;

import com.jeekrs.neuro_simulation.components.abilities.CanEat;
import com.jeekrs.neuro_simulation.components.abilities.Fighting;
import com.jeekrs.neuro_simulation.components.abilities.Hittable;
import com.jeekrs.neuro_simulation.components.abilities.Movable;
import com.jeekrs.neuro_simulation.components.data.NeuralNetwork;
import com.jeekrs.neuro_simulation.components.data.Position;
import com.jeekrs.neuro_simulation.components.effectors.Effector;
import com.jeekrs.neuro_simulation.components.effectors.Legs;
import com.jeekrs.neuro_simulation.components.sensors.Eyes;
import com.jeekrs.neuro_simulation.components.sensors.Sensor;
import com.jeekrs.neuro_simulation.neural_network.Link;
import com.jeekrs.neuro_simulation.neural_network.Neuron;
import com.jeekrs.neuro_simulation.utils.RandomUtil;

public class Swarm extends Entity {
    public float radius = 32;
    public Eyes eyes = new Eyes();
    public Legs legs = new Legs();
    public NeuralNetwork network = new NeuralNetwork(new Sensor[]{eyes}, new Effector[]{legs}, new int[]{3, 3});

    public Swarm(float x, float y) {
        CanEat c1 = new CanEat();
        Position pos = new Position(x, y);
        Fighting f = new Fighting() {{
            damage = 10;
            defense = 10;
            health = 100;
            health_limit = 100;
        }};


        putComponent("can_eat", c1);
        putComponent("pos", pos);
        putComponent("fight", f);
        putComponent("eyes", eyes);
        putComponent("legs", legs);
        putComponent("network", network);
        putComponent("hittable", new Hittable() {
            @Override
            public boolean contains(float x, float y) {
                return (pos.x - x) * (pos.x - x) + (pos.y - y) * (pos.y - y) <= radius * radius;
            }
        });

        putComponent("movable", new Movable() {
            @Override
            public Position getPos() {
                return pos;
            }
        });
    }

    public void randomLink(int n) {
        while (n-- > 0) {
            Neuron[][] nodes = network.network.nodes;
            int i = RandomUtil.nextInt(0, nodes.length);
            int j = RandomUtil.nextInt(0, nodes.length);
            if (i > j)
                i = i ^ j ^ (j = i);
            else if (i == j) {
                ++n;
                continue;
            }
            int a = RandomUtil.nextInt(0, nodes[i].length);
            int b = RandomUtil.nextInt(0, nodes[j].length);
            Link.makeLink(nodes[i][a], nodes[j][b], RandomUtil.nextFloat());
        }
    }
}
