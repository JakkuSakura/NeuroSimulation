package com.jeekrs.neuro_simulation.system;

import com.jeekrs.neuro_simulation.components.abilities.Edible;
import com.jeekrs.neuro_simulation.components.abilities.Fighting;
import com.jeekrs.neuro_simulation.components.data.NeuralNetwork;
import com.jeekrs.neuro_simulation.components.data.Position;
import com.jeekrs.neuro_simulation.components.effectors.Legs;
import com.jeekrs.neuro_simulation.entities.Entity;
import com.jeekrs.neuro_simulation.components.sensors.Eyes;


import java.util.ArrayList;
import java.util.Objects;
import java.util.TreeSet;

public class EntitySystem extends SimpleSystem {
    public ArrayList<Entity> dead = new ArrayList<>();
    public ArrayList<Entity> newborn = new ArrayList<>();

    public TreeSet<Entity> entities = new TreeSet<>();

    public void update(float delta) {
        sensor_update(delta);
        activate_network();
        effector_action(delta);

        health();

        entities.removeAll(dead);
        entities.addAll(newborn);
        dead.clear();
        newborn.clear();
    }

    private void sensor_update(float delta) {
        entities.stream().filter(entity -> entity.hasComponentByClass(Eyes.class) && entity.hasComponentByClass(Position.class))
                .forEach(e -> {
                            Eyes eyes = e.getComponentByClass(Eyes.class);
                            Position pos = e.getComponentByClass(Position.class);
                            eyes.neurons[0].update(entities.stream()
                                    .filter(x -> x.hasComponentByClass(Edible.class) && x.hasComponentByClass(Position.class))
                                    .filter(x -> x.getComponentByClass(Position.class).dst2(pos) <= eyes.limit * eyes.limit)
                                    .count());


                        }

                );
    }

    private void activate_network() {
        entities.stream().map(e -> e.getComponentByClass(NeuralNetwork.class)).filter(Objects::nonNull)
                .forEach(network -> network.network.activate());
    }

    private void effector_action(float delta) {
        entities.stream().filter(entity -> entity.hasComponentByClass(Legs.class) && entity.hasComponentByClass(Position.class))
                .forEach(e -> {
                            Position pos = e.getComponentByClass(Position.class);
                            Legs legs = e.getComponentByClass(Legs.class);
                            pos.add(legs.velocity().scl(delta));
                        }

                );
    }

    private void health() {
        entities.stream()
                .filter(x -> x.hasComponentByClass(Fighting.class))
                .filter(x -> x.getComponentByClass(Fighting.class).health <= 0)
                .forEach(x -> dead.add(x));
    }

    private void movement(float delta) {

    }

    private void livingUpdate() {
        entities.forEach(entity -> {
//            entity.update();
        });
    }

    public boolean addEntity(Entity p1) {
        return newborn.add(p1);
    }
}
