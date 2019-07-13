package com.jeekrs.neuro_simulation.entities.nest;

import com.jeekrs.neuro_simulation.Agenda;
import com.jeekrs.neuro_simulation.entities.Structure;
import com.jeekrs.neuro_simulation.entities.livings.Living;
import com.jeekrs.neuro_simulation.interfaces.Alive;

public class Nest extends Structure implements Alive {
    private long delay_ms = 1000;
    private Living prototype;
    private float health_limit = 300;
    private float health = 300;
    private Agenda agenda;
    private long last_time;

    public Living getPrototype() {
        return prototype;
    }

    public void setPrototype(Living prototype) {
        this.prototype = prototype;
        prototype.getPos().set(getPos().x, getPos().y);
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

    public Agenda getAgenda() {
        return agenda;
    }

    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
        if (prototype != null)
            prototype.setAgenda(agenda);
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
}
