package com.jeekrs.neuro_simulation.systems;

import com.jeekrs.neuro_simulation.components.abilities.*;
import com.jeekrs.neuro_simulation.components.data.*;
import com.jeekrs.neuro_simulation.components.effectors.Effector;
import com.jeekrs.neuro_simulation.components.effectors.Legs;
import com.jeekrs.neuro_simulation.components.effectors.Reproduction;
import com.jeekrs.neuro_simulation.components.sensors.Eyes;
import com.jeekrs.neuro_simulation.components.sensors.HealthSensor;
import com.jeekrs.neuro_simulation.components.sensors.Sensor;
import com.jeekrs.neuro_simulation.entities.AntNest;
import com.jeekrs.neuro_simulation.entities.Entity;
import com.jeekrs.neuro_simulation.entities.Ant;
import com.jeekrs.neuro_simulation.entities.Food;
import com.jeekrs.neuro_simulation.populations.Population;

import java.util.*;

public class EntitySystem extends SimpleSystem {

    public HashMap<Class, Population> populations = new HashMap<>();

    @Override
    public void init() {
        populations.put(Food.class, new Population());
        populations.put(Ant.class, new Population());
        populations.put(AntNest.class, new Population() {{
            addNewborn(new AntNest(0, 0));
        }});
    }

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
        populations.forEach((k, v) -> v.update());
    }

    private void network_input() {
        populations.values().forEach(population ->
                population.getEntities().stream().filter(e -> e.hasComponentByClass(NeuralNetworkAdapter.class))
                        .forEach(e -> {
                            ArrayList<Sensor> sensors = e.getComponentListByClass(Sensor.class);
                            e.getComponentByClass(NeuralNetworkAdapter.class).input(sensors.toArray(new Sensor[0]));
                        }));

    }


    private void activate_network() {
        populations.values().forEach(population ->
                population.getEntities().stream().map(e -> e.getComponentByClass(NeuralNetworkAdapter.class)).filter(Objects::nonNull)
                        .forEach(NeuralNetworkAdapter::activate));
    }

    private void network_output() {
        populations.values().forEach(population ->
                population.getEntities().stream().filter(e -> e.hasComponentByClass(NeuralNetworkAdapter.class))
                        .forEach(e -> {
                            ArrayList<Effector> effectors = e.getComponentListByClass(Effector.class);
                            e.getComponentByClass(NeuralNetworkAdapter.class).output(effectors.toArray(new Effector[0]));

                        }));

    }

    private void consume() {
        ArrayList<Entity> food = populations.get(Food.class).getEntities();
        populations.values().forEach(population ->
                population.getEntities().stream().filter(entity -> entity.hasComponentByClass(CanEat.class) && entity.hasComponentByClass(Hittable.class) && entity.hasComponentByClass(Fighting.class))
                        .forEach(entity -> {
                            Hittable hittable = entity.getComponentByClass(Hittable.class);
                            food.stream()
                                    .filter(e -> e.hasComponentByClass(Edible.class))
                                    .filter(e -> hittable.contains(Position.getPosition(e)))
                                    .findFirst()
                                    .ifPresent(e -> {
                                        Fighting fighting = entity.getComponentByClass(Fighting.class);
                                        fighting.health += e.getComponentByClass(Edible.class).health;
                                        fighting.adjustHealth();
                                        fighting.energy += e.getComponentByClass(Edible.class).energy;
                                        fighting.adjustEnergy();

                                        // fixme does not disappear after being eaten
                                        e.removeComponent(e.getComponentByClass(Edible.class));
                                        populations.get(Food.class).addDead(e);
                                    });
                        }));
    }

    private void hungry(float delta) {
        populations.values().forEach(population ->
                population.getEntities().stream()
                        .filter(entity -> entity.hasComponentByClass(Hungry.class) && entity.hasComponentByClass(Fighting.class))
                        .forEach(entity -> entity.getComponentByClass(Fighting.class).health -= entity.getComponentByClass(Hungry.class).hungry_rate * delta));
    }

    private void sensor_update(float delta) {
        populations.values().forEach(population ->
                population.
                        getEntities().stream().filter(entity -> entity.hasComponentByClass(Eyes.class) && Position.hasPosition(entity))
                        .forEach(e -> {
                                    Eyes eyes = e.getComponentByClass(Eyes.class);
                                    Position pos = Position.getPosition(e);
                                    float[] xv = {0, 0};
                                    populations.get(Food.class).getEntities().stream()
                                            .filter(x -> Position.getPosition(x).dst2(pos) <= eyes.limit * eyes.limit)
                                            .limit(5).forEach(x -> {
                                        Edible edible = x.getComponentByClass(Edible.class);
                                        Position position = Position.getPosition(x);
                                        xv[0] += (pos.x - position.x);
                                        xv[1] += (pos.y - position.y);

                                    });
                                    eyes.inputs[0] = xv[0];
                                    eyes.inputs[1] = xv[1];

                                    xv[0] = xv[1] = 0;
                                    populations.values().forEach(pop -> {
                                        pop.getEntities().stream()
                                                .filter(Position::hasPosition)
                                                .filter(x -> Position.getPosition(x).dst2(pos) <= eyes.limit * eyes.limit)
                                                .limit(5)
                                                .forEach(x -> {
                                                    Position position = Position.getPosition(x);
                                                    xv[0] += (pos.x - position.x);
                                                    xv[1] += (pos.y - position.y);

                                                });
                                        eyes.inputs[2] = xv[0];
                                        eyes.inputs[3] = xv[1];
                                    });
                                }

                        ));
        populations.values().forEach(population ->
                population.
                        getEntities().stream().filter(entity -> entity.hasComponentByClass(HealthSensor.class) && entity.hasComponentByClass(Fighting.class))
                        .forEach(e -> {
                                    HealthSensor health = e.getComponentByClass(HealthSensor.class);
                                    Fighting fighting = e.getComponentByClass(Fighting.class);
                                    health.inputs[0] = fighting.health;
                                    health.inputs[1] = fighting.health_limit;

                                }

                        ));
    }

    private void effector_action(float delta) {
        populations.values().forEach(population ->
                population.
                        getEntities().stream().filter(entity -> entity.hasComponentByClass(Legs.class) && Position.hasPosition(entity))
                        .forEach(e -> {
                                    Position pos = Position.getPosition(e);
                                    Legs legs = e.getComponentByClass(Legs.class);
                                    legs.calculateAcceleration(delta);
                                    Vec2f velocity = legs.velocity();
//                            System.out.println(velocity);
                                    if (!velocity.isZero() && e.hasComponentByClass(Rotation.class))
                                        e.getComponentByClass(Rotation.class).rotation = velocity.angle();
                                    pos.add(velocity.scl(delta));

                                    // to filter out unmovable creatures
                                    if (velocity.dst2(0, 0) < .1)
                                        population.addDead(e);
                                }

                        ));
        populations.values().forEach(population ->
                population.
                        getEntities().stream().filter(entity -> entity.hasComponentByClass(Reproduction.class) && Position.hasPosition(entity))
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


                                                population.addNewborn(clone);
                                            }
                                        }
                                    }
                                }

                        ));
    }

    private void health() {
        populations.values().forEach(population ->
                population.getEntities().stream()
                        .filter(x -> x.hasComponentByClass(Fighting.class))
                        .filter(x -> x.getComponentByClass(Fighting.class).health <= 0)
                        .forEach(population::addDead));
    }


}
