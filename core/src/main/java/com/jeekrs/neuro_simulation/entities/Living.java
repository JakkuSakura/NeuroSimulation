package com.jeekrs.neuro_simulation.entities;


import com.badlogic.gdx.math.Vector2;
import com.jeekrs.neuro_simulation.component.Circle;
import com.jeekrs.neuro_simulation.component.Movable;
import com.jeekrs.neuro_simulation.effectors.Effector;
import com.jeekrs.neuro_simulation.processors.Processor;
import com.jeekrs.neuro_simulation.sensories.Sensory;
import com.jeekrs.neuro_simulation.utils.Cloner;
import com.jeekrs.neuro_simulation.utils.Package;

import java.util.ArrayList;

public class Living extends Entity implements Movable, Circle {
    private String name = getClass().getSimpleName() + getClass().hashCode();
    private Processor processor;
    private ArrayList<Sensory> sensories = new ArrayList<>();
    private ArrayList<Effector> effects = new ArrayList<>();
    private float direction;
    protected float radius = 30;

    public Living() {
    }

    protected void addSensory(Sensory s) {
        sensories.add(s);
    }

    protected void addEffector(Effector e) {
        effects.add(e);
    }

    public int sensoryPackageLength() {
        return detect().vals.size();
    }

    public int effectorPackageLength() {
        return effects.stream().mapToInt(Effector::neededLength).sum();
    }

    public Processor getProcessor() {
        return processor;
    }

    public Package detect() {
        Package p = new Package();
        sensories.forEach(e -> p.append(e.detect(this)));
        return p;
    }

    public Package process(Package p) {
        return processor.process(p);
    }

    public void effect(Package p) {
        for (int i = 0, j = 0; i < effects.size(); i++) {
            effects.get(i).effect(p.slice(j, effects.get(i).neededLength()), this);
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


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getRadius() {
        return radius;
    }

    @Override
    public com.badlogic.gdx.math.Circle getCircle() {
        return new com.badlogic.gdx.math.Circle(getPos().x, getPos().y, radius);
    }

    @Override
    public boolean contains(Vector2 point) {
        return getPos().dst2(point) <= getRadius() * getRadius();
    }

    @Override
    public boolean contains(float x, float y) {
        return getPos().dst2(x, y) < radius * radius;
    }


    @Override
    public Living clone() {
        Living living = (Living) super.clone();
        living.processor = living.processor.clone();

        living.effects = Cloner.deepCopy(living.effects);
        living.sensories = Cloner.deepCopy(living.sensories);

        return living;
    }

    @Override
    public Vector2 getPos() {
        return phy.pos;
    }

    @Override
    public Vector2 getVel() {
        return phy.vel;
    }

    @Override
    public Vector2 getAcc() {
        return phy.acc;
    }
}
