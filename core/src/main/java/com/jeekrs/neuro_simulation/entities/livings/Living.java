package com.jeekrs.neuro_simulation.entities.livings;


import com.badlogic.gdx.math.Vector2;
import com.jeekrs.neuro_simulation.Agenda;
import com.jeekrs.neuro_simulation.entities.Entity;
import com.jeekrs.neuro_simulation.interfaces.Alive;
import com.jeekrs.neuro_simulation.interfaces.Circle;
import com.jeekrs.neuro_simulation.interfaces.Fightable;
import com.jeekrs.neuro_simulation.interfaces.Movable;
import com.jeekrs.neuro_simulation.utils.RandomUtil;

public class Living extends Entity implements Movable, Circle, Alive, Fightable {
    private String name = getClass().getSimpleName() + getClass().hashCode();
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

    public float getDefence() {
        return defence;
    }

    public void setDamage(float damage) {
        this.damage = damage;
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

        living.health *= RandomUtil.nextFloat(0.98f, 1.02f);
        living.health_limit *= RandomUtil.nextFloat(0.98f, 1.02f);
        living.defence *= RandomUtil.nextFloat(0.98f, 1.02f);
        living.damage *= RandomUtil.nextFloat(0.98f, 1.02f);
        return living;
    }
}
