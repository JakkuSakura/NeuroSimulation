package com.jeekrs.neuro_simulation.interfaces;

import com.jeekrs.neuro_simulation.Agenda;

public interface Fightable extends Position {
    float getDamage();

    float getDefence();

    Agenda getAgenda();

    float getHealth();

    void setHealth(float health);

    float getHealthLimit();

    void setHealthLimit(float health_limit);
}
