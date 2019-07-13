package com.jeekrs.neuro_simulation.system;

import com.jeekrs.neuro_simulation.Agenda;
import com.jeekrs.neuro_simulation.Species.Species;

import java.util.HashMap;

public class AgendaSystem extends SimpleSystem {
    public HashMap<Agenda, Species> agendas = new HashMap<>();

    public Agenda playerAgenda;
}
