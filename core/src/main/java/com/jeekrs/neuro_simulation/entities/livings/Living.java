package com.jeekrs.neuro_simulation.entities.livings;


import com.badlogic.gdx.math.Vector2;
import com.jeekrs.neuro_simulation.Agenda;
import com.jeekrs.neuro_simulation.effectors.Effector;
import com.jeekrs.neuro_simulation.entities.Entity;
import com.jeekrs.neuro_simulation.interfaces.Alive;
import com.jeekrs.neuro_simulation.interfaces.Circle;
import com.jeekrs.neuro_simulation.interfaces.Movable;
import com.jeekrs.neuro_simulation.processors.NeuroProcessor;
import com.jeekrs.neuro_simulation.processors.Processor;
import com.jeekrs.neuro_simulation.sensories.Sensory;
import com.jeekrs.neuro_simulation.utils.Cloner;
import com.jeekrs.neuro_simulation.utils.Package;
import com.jeekrs.neuro_simulation.utils.RandomUtil;

import java.util.ArrayList;

public class Living extends Entity implements Movable, Circle, Alive {
    private String name = getClass().getSimpleName() + getClass().hashCode();
    private Processor processor;
    private ArrayList<Sensory> sensories = new ArrayList<>();
    private ArrayList<Effector> effects = new ArrayList<>();
    private Agenda agenda;
    private Vector2 vel = new Vector2();
    private float health = 100;
    private float health_limit = 100;
    private float damage = 10;
    private float defence = 10;
    protected float radius = 30;
    private float direction = 0;

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
        living.processor = processor.clone();
        living.effects = Cloner.deepCopy(effects);
        living.sensories = Cloner.deepCopy(sensories);
        living.vel = new Vector2(vel);

        return living;
    }


    @Override
    public Vector2 getVel() {
        return vel;
    }

    @Override
    public float getHealth() {
        return health;
    }

    @Override
    public void setHealth(float health) {
        this.health = health;
    }

    @Override
    public float getHealthLimit() {
        return health_limit;
    }

    @Override
    public void setHealthLimit(float health_limit) {
        this.health_limit = health_limit;
    }

    public float getDamage() {
        return damage;
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }

    public float getDefence() {
        return defence;
    }

    public void setDefence(float defence) {
        this.defence = defence;
    }

    public Agenda getAgenda() {
        return agenda;
    }

    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }

    public Living breed() {
        Living living = clone();
        living.getPos().x += RandomUtil.nextFloat(-50, 50);
        living.getPos().y += RandomUtil.nextFloat(-50, 50);
        if (living.getProcessor() instanceof NeuroProcessor) {
            ((NeuroProcessor) living.getProcessor()).adjust(0.2f);
        }
        living.health *= RandomUtil.nextFloat(0.98f, 1.02f);
        living.health_limit *= RandomUtil.nextFloat(0.98f, 1.02f);
        living.defence *= RandomUtil.nextFloat(0.98f, 1.02f);
        living.damage *= RandomUtil.nextFloat(0.98f, 1.02f);
        return living;
    }
}
