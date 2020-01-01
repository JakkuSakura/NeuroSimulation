package com.jeekrs.neuro_simulation.system;

import com.jeekrs.neuro_simulation.components.abilities.*;
import com.jeekrs.neuro_simulation.components.data.*;
import com.jeekrs.neuro_simulation.components.effectors.Legs;
import com.jeekrs.neuro_simulation.components.effectors.Reproduction;
import com.jeekrs.neuro_simulation.components.sensors.Eyes;
import com.jeekrs.neuro_simulation.entities.Entity;
import com.jeekrs.neuro_simulation.entities.Swarm;
import com.jeekrs.neuro_simulation.neural_network.Link;
import com.jeekrs.neuro_simulation.utils.RandomUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class EntitySystem extends SimpleSystem {
    public ArrayList<Entity> dead = new ArrayList<>();
    public ArrayList<Entity> newborn = new ArrayList<>();

    public TreeSet<Entity> entities = new TreeSet<>();

    public void update(float delta) {
        delta *= 20;
        sensor_update(delta);
        activate_network();
        effector_action(delta);

        consume();
        hungry(delta);
        health();

        entities.removeAll(dead);
        entities.addAll(newborn);
        dead.clear();
        newborn.clear();
    }

    private void consume() {
        List<Entity> food = entities.stream().filter(entity -> Position.hasPosition(entity) && entity.hasComponentByClass(Edible.class)).collect(Collectors.toList());
        entities.stream().filter(entity -> entity.hasComponentByClass(CanEat.class) && entity.hasComponentByClass(Hittable.class) && entity.hasComponentByClass(Fighting.class))
                .forEach(entity -> {
                    Hittable hittable = entity.getComponentByClass(Hittable.class);
                    food.stream()
                            .filter(e -> hittable.contains(Position.getPosition(e)))
                            .findFirst()
                            .ifPresent(e -> {
                                Fighting fighting = entity.getComponentByClass(Fighting.class);
                                fighting.health += e.getComponentByClass(Edible.class).health;
                                fighting.adjustHealth();
                                fighting.energy += e.getComponentByClass(Edible.class).energy;
                                fighting.adjustEnergy();

                                food.remove(e);
                                removeEntity(e);
                            });
                });
    }

    private void hungry(float delta) {
        entities.stream()
                .filter(entity -> entity.hasComponentByClass(Hungry.class) && entity.hasComponentByClass(Fighting.class))
                .forEach(entity -> entity.getComponentByClass(Fighting.class).health -= entity.getComponentByClass(Hungry.class).hungry_rate * delta);
    }

    private void sensor_update(float delta) {
        entities.stream().filter(entity -> entity.hasComponentByClass(Eyes.class) && Position.hasPosition(entity))
                .forEach(e -> {
                            Eyes eyes = e.getComponentByClass(Eyes.class);
                            Position pos = Position.getPosition(e);
                            float[] xv = {0, 0};
                            entities.stream()
                                    .filter(x -> x.hasComponentByClass(Edible.class) && Position.hasPosition(x))
                                    .filter(x -> Position.getPosition(x).dst2(pos) <= eyes.limit * eyes.limit)
                                    .findFirst().ifPresent(x -> {
                                Edible edible = x.getComponentByClass(Edible.class);
                                Position position = Position.getPosition(x);
                                xv[0] += (pos.x - position.x);
                                xv[1] += (pos.y - position.y);

                            });
                            eyes.neurons[0].update(xv[0]);
                            eyes.neurons[1].update(xv[1]);

                            xv[0] = xv[1] = 0;
                            entities.stream()
                                    .filter(Position::hasPosition)
                                    .filter(x -> Position.getPosition(x).dst2(pos) <= eyes.limit * eyes.limit)
                                    .findFirst()
                                    .ifPresent(x -> {
                                        Position position = Position.getPosition(x);
                                        xv[0] += (pos.x - position.x);
                                        xv[1] += (pos.y - position.y);

                                    });
                            eyes.neurons[2].update(xv[0]);
                            eyes.neurons[3].update(xv[1]);

                        }

                );
    }

    private void activate_network() {
        entities.stream().map(e -> e.getComponentByClass(NeuralNetwork.class)).filter(Objects::nonNull)
                .forEach(network -> network.network.activate());
    }

    private void effector_action(float delta) {
        entities.stream().filter(entity -> entity.hasComponentByClass(Legs.class) && Position.hasPosition(entity))
                .forEach(e -> {
                            Position pos = Position.getPosition(e);
                            Legs legs = e.getComponentByClass(Legs.class);
                            Vec2f velocity = legs.velocity();
//                            System.out.println(velocity);
                            if (!velocity.isZero() && e.hasComponentByClass(Rotation.class))
                                e.getComponentByClass(Rotation.class).rotation = velocity.angle();
                            pos.add(velocity.scl(delta));
                        }

                );

        entities.stream().filter(entity -> entity.hasComponentByClass(Reproduction.class) && Position.hasPosition(entity))
                .forEach(e -> {
                            Reproduction reproduction = e.getComponentByClass(Reproduction.class);
                            if (reproduction.effector_neurons[0].v >= reproduction.threshold) {
                                if (e instanceof Swarm) {
                                    Swarm s = (Swarm) e;
                                    if (s.fighting().energy >= reproduction.energy_consumption) {
                                        s.fighting().energy -= reproduction.energy_consumption;

                                        Swarm clone = (Swarm) s.clone();
                                        clone.putComponent("parent", new Parent(s));
                                        clone.pos().x += 20;
                                        clone.pos().y += 20;
                                        clone.fighting().health = 80;

                                        for (Link link : clone.network().network.links) {
                                            link.weight *= RandomUtil.nextFloat(0.8f, 1.2f);
                                        }

                                        addEntity(clone);
                                    }
                                }
                            }
                        }

                );
    }

    private void health() {
        entities.stream()
                .filter(x -> x.hasComponentByClass(Fighting.class))
                .filter(x -> x.getComponentByClass(Fighting.class).health <= 0)
                .forEach(x -> dead.add(x));
    }

    public boolean removeEntity(Entity p1) {
        return dead.add(p1);
    }

    public boolean addEntity(Entity p1) {
        return newborn.add(p1);
    }

    public static void main(String[] args) {
        Swarm s = new Swarm(0, 0);
        s.randomLink(RandomUtil.nextInt(15, 30));
        Swarm swarm = (Swarm) s.clone();
        swarm.pos().x += 10;
        swarm.pos().y += 10;
        swarm.fighting().health = 50;
        for (Link link : swarm.network().network.links) {
            link.weight *= RandomUtil.nextFloat(0.8f, 1.2f);
        }
        s.network().network.activate();
        s.network().network.printValues();
        swarm.network().network.activate();
        swarm.network().network.printValues();
        System.out.println("done");
    }
}
