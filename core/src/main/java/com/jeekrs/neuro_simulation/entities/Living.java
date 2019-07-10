package com.jeekrs.neuro_simulation.entities;

import com.jeekrs.neuro_simulation.component.Circle;
import com.jeekrs.neuro_simulation.component.Movable;
import com.jeekrs.neuro_simulation.component.Physics;
import com.jeekrs.neuro_simulation.sensories.Sensory;
import com.jeekrs.neuro_simulation.effectors.Effector;
import com.jeekrs.neuro_simulation.processors.Processor;
import com.jeekrs.neuro_simulation.utils.Package;
import com.jeekrs.neuro_simulation.utils.RandomUtil;
import com.jeekrs.neuro_simulation.utils.Vec2d;

import java.util.ArrayList;
import java.util.Random;

public class Living extends Entity implements Movable, Circle {
    private String name = getClass().getSimpleName() + getClass().hashCode();
    private Processor processor;
    private ArrayList<Sensory> sensories = new ArrayList<>();
    private ArrayList<Effector> effects = new ArrayList<>();
    private float direction;
    protected double radius = 30;

    public Living() {
    }

    protected void addSensory(Sensory s) {
        sensories.add(s);
    }

    protected void addEffector(Effector e) {
        effects.add(e);
    }

    public int sensoryPackageLength() {
        return collect().vals.size();
    }

    public int effectorPackageLength() {
        return effects.stream().mapToInt(Effector::neededLength).sum();
    }

    public Package collect() {
        Package p = new Package();
        sensories.forEach(e -> p.append(e.detect()));
        return p;
    }

    public Package process(Package p) {
        return processor.process(p);
    }

    public void effect(Package p) {
        for (int i = 0, j = 0; i < effects.size(); i++) {
            effects.get(i).effect(p.slice(j, effects.get(i).neededLength()));
        }
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int compareTo(Entity entity) {
        return Integer.compare(hashCode(), entity.hashCode());
    }

    public float getDirection() {
        return direction;
    }

    public void setDirection(float direction) {
        this.direction = direction;
    }

    public void setProcessor(Processor processor) {
        this.processor = processor;
    }

    public void initProcessor() {
        processor.init();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public double getRadius() {
        return radius;
    }

    @Override
    public boolean hitCheck(Vec2d pos) {
        return getPhy().pos.distSquare(pos) <= getRadius() * getRadius();
    }

    @Override
    public Vec2d getInnerPoint() {
        Vec2d p = new Vec2d(getPhy().pos.x + RandomUtil.nextDouble(-1, 1) * radius, getPhy().pos.y + RandomUtil.nextDouble(-1, 1) * radius);
        return p;


    }
}
