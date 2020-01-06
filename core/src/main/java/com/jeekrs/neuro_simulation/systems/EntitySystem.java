package com.jeekrs.neuro_simulation.systems;

import com.jeekrs.neuro_simulation.components.abilities.*;
import com.jeekrs.neuro_simulation.components.data.*;
import com.jeekrs.neuro_simulation.components.effectors.Effector;
import com.jeekrs.neuro_simulation.components.effectors.Legs;
import com.jeekrs.neuro_simulation.components.effectors.Reproduction;
import com.jeekrs.neuro_simulation.components.sensors.Eyes;
import com.jeekrs.neuro_simulation.components.sensors.HealthSensor;
import com.jeekrs.neuro_simulation.components.sensors.Sensor;
import com.jeekrs.neuro_simulation.entities.Entity;
import com.jeekrs.neuro_simulation.entities.Ant;

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
        network_input();
        activate_network();
        network_output();
        effector_action(delta);

        consume();
        hungry(delta);
        health();

        entities.removeAll(dead);
        entities.addAll(newborn);
        dead.clear();
        newborn.clear();
    }

    private void network_input() {
        entities.stream().filter(e -> e.hasComponentByClass(NeuralNetworkAdapter.class))
                .forEach(e -> {
                    ArrayList<Sensor> sensors = e.getComponentListByClass(Sensor.class);
                    e.getComponentByClass(NeuralNetworkAdapter.class).input(sensors.toArray(new Sensor[0]));
                });

    }


    private void activate_network() {
        entities.stream().map(e -> e.getComponentByClass(NeuralNetworkAdapter.class)).filter(Objects::nonNull)
                .forEach(NeuralNetworkAdapter::activate);
    }

    private void network_output() {
        entities.stream().filter(e -> e.hasComponentByClass(NeuralNetworkAdapter.class))
                .forEach(e -> {
                    ArrayList<Effector> effectors = e.getComponentListByClass(Effector.class);
                    e.getComponentByClass(NeuralNetworkAdapter.class).output(effectors.toArray(new Effector[0]));

                });

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
                            eyes.inputs[0] = xv[0];
                            eyes.inputs[1] = xv[1];

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
                            eyes.inputs[2] = xv[0];
                            eyes.inputs[3] = xv[1];

                        }

                );
        entities.stream().filter(entity -> entity.hasComponentByClass(HealthSensor.class) && entity.hasComponentByClass(Fighting.class))
                .forEach(e -> {
                            HealthSensor health = e.getComponentByClass(HealthSensor.class);
                            Fighting fighting = e.getComponentByClass(Fighting.class);
                            health.inputs[0] = fighting.health;
                            health.inputs[1] = fighting.health_limit;

                        }

                );
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
                            // todo to filter out unmovable creatures
                            if (velocity.dst2(0,0) < 1)
                                removeEntity(e);
                        }

                );

        entities.stream().filter(entity -> entity.hasComponentByClass(Reproduction.class) && Position.hasPosition(entity))
                .forEach(e -> {
                            Reproduction reproduction = e.getComponentByClass(Reproduction.class);
                            if (reproduction.outputs[0] >= reproduction.threshold) {
                                if (e instanceof Ant) {
                                    Ant s = (Ant) e;
                                    if (s.fighting().energy >= reproduction.energy_consumption) {
                                        s.fighting().energy -= reproduction.energy_consumption;

                                        Ant clone = (Ant) s.clone();
                                        clone.putComponent("parent", new Parent(s));
                                        clone.pos().x += 20;
                                        clone.pos().y += 20;
                                        clone.fighting().health = 80;

                                        for (int i = 0; i < 2; i++) {
                                            clone.network().mutate();
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
                .forEach(this::removeEntity);
    }

    public boolean removeEntity(Entity p1) {
        return dead.add(p1);
    }

    public boolean addEntity(Entity p1) {
        return newborn.add(p1);
    }

}
