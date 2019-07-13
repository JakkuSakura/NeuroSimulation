package com.jeekrs.neuro_simulation.interfaces;

import com.jeekrs.neuro_simulation.Agenda;

public interface Fightable extends Alive, Position {
    float getDamage();

    float getDefence();

    Agenda getAgenda();
}
