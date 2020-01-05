package com.jeekrs.neuro_simulation.entities;

import com.jeekrs.neural_network.dense.DenseLayers;
import com.jeekrs.neuro_simulation.components.abilities.*;
import com.jeekrs.neuro_simulation.components.data.DenseLayersAdapter;
import com.jeekrs.neuro_simulation.components.data.NeuralNetworkAdapter;
import com.jeekrs.neuro_simulation.components.data.Position;
import com.jeekrs.neuro_simulation.components.data.Rotation;
import com.jeekrs.neuro_simulation.components.effectors.Effector;
import com.jeekrs.neuro_simulation.components.effectors.Legs;
import com.jeekrs.neuro_simulation.components.effectors.Reproduction;
import com.jeekrs.neuro_simulation.components.sensors.Eyes;
import com.jeekrs.neuro_simulation.components.sensors.Sensor;
import com.jeekrs.neural_network.dense.Neuron;
import com.jeekrs.neuro_simulation.utils.RandomUtil;

public class Ant extends Entity {
    public float radius = 32;

    public Ant(float x, float y) {
        Fighting fighting;
        CanEat c1 = new CanEat();
        Position pos = new Position(x, y);
        fighting = new Fighting() {{
            damage = 10;
            defense = 10;
            energy = 10;
            energy_limit = 100;
            health = 100;
            health_limit = 100;
        }};

        Eyes eyes = new Eyes(200);
        Legs legs = new Legs();
        Reproduction reproduction = new Reproduction(50);
        NeuralNetworkAdapter network = new DenseLayersAdapter(new Sensor[]{eyes}, new Effector[]{legs, reproduction}, new int[]{6, 6, 6});

        putComponent("can_eat", c1);
        putComponent("pos", pos);
        putComponent("fighting", fighting);
        putComponent("eyes", eyes);
        putComponent("legs", legs);
        putComponent("network", network);
        putComponent("rotation", new Rotation());
        putComponent("hungry", new Hungry(3));
        putComponent("reproduction", reproduction);
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


    public NeuralNetworkAdapter network() {
        return getComponentByNameAndClass("network", NeuralNetworkAdapter.class);

    }

    public void randomLink(int n) {

    }

    public Position pos() {
        return Position.getPosition(this);
    }

    public Fighting fighting() {
        return Fighting.getFighting(this);
    }

    @Override
    public String toString() {
        return String.format("Swarm{pos=%s, health=%f}", pos(), fighting().health);
    }
}
