package com.jeekrs.neuro_simulation.entities;

import com.jeekrs.neural_network.dense.DenseLayers;
import com.jeekrs.neuro_simulation.components.abilities.*;
import com.jeekrs.neuro_simulation.components.data.*;
import com.jeekrs.neuro_simulation.components.effectors.Effector;
import com.jeekrs.neuro_simulation.components.effectors.Legs;
import com.jeekrs.neuro_simulation.components.effectors.Reproduction;
import com.jeekrs.neuro_simulation.components.sensors.*;
import com.jeekrs.neuro_simulation.utils.RandomUtil;


public class Ant extends Entity {
    public float radius = 32;

    public Ant(float x, float y) {
        Fighting fighting;
        CanEat can_eat = new CanEat();
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
        RandomSensor randomSensor = new RandomSensor();
        ConstantSensor constantSensor = new ConstantSensor();
        HealthSensor healthSensor = new HealthSensor();
        Reproduction reproduction = new Reproduction(20);
        NeuralNetworkAdapter network;

        Sensor[] sensors = {eyes, randomSensor, constantSensor, healthSensor};
        Effector[] effectors = {legs, reproduction};

        if (RandomUtil.success(0.2f))
            network = new DenseLayersAdapter(sensors, effectors, new int[]{6, 6, 6});
        else {
            network = new NeatAdapter(sensors, effectors, 30);
        }

        putComponent("can_eat", can_eat);
        putComponent("pos", pos);
        putComponent("fighting", fighting);
        putComponent("eyes", eyes);
        putComponent("legs", legs);
        putComponent("network", network);
        putComponent("random", randomSensor);
        putComponent("health_sensor", healthSensor);
        putComponent("constant", constantSensor);
        putComponent("rotation", new Rotation());
        putComponent("hungry", new Hungry(1));
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
