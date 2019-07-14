package com.jeekrs.neuro_simulation.interfaces;

import com.jeekrs.neuro_simulation.Agenda;

public interface Alive {
    float getHealth();

    void setHealth(float health);

    float getHealthLimit();

    void setHealthLimit(float health_limit);
    Agenda getAgenda();
}
