package com.jeekrs.neuro_simulation.entities;

import com.badlogic.gdx.math.Vector2;
import com.jeekrs.neuro_simulation.Agenda;
import com.jeekrs.neuro_simulation.Species.Species;
import com.jeekrs.neuro_simulation.interfaces.Alive;
import com.jeekrs.neuro_simulation.interfaces.Rectangle;

public class BaseCamp extends Structure implements Alive, Rectangle {
    private long delay_ms = 1000;
    private float health_limit = 300;
    private float health = 300;

    private Agenda agenda;
    private Species species;
    private long last_time;
    private float width = 64;
    private float height = 64;

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

    public Agenda getAgenda() {
        return agenda;
    }

    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }

    public long getDelayMs() {
        return delay_ms;
    }

    public void setDelayMs(long delay_ms) {
        this.delay_ms = delay_ms;
    }

    public long getLastTime() {
        return last_time;
    }

    public void setLastTime(long last_time) {
        this.last_time = last_time;
    }

    @Override
    public com.badlogic.gdx.math.Rectangle getRectangle() {
        return new com.badlogic.gdx.math.Rectangle(getPos().x - width / 2, getPos().y - height / 2, width, height);
    }

    @Override
    public boolean contains(Vector2 point) {
        return getRectangle().contains(point);
    }

    @Override
    public boolean contains(float x, float y) {
        return getRectangle().contains(x, y);
    }

    public Species getSpecies() {
        return species;
    }

    public void setSpecies(Species species) {
        this.species = species;
    }
}
