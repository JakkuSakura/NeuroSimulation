package com.jeekrs.neuro_simulation.game;

import com.jeekrs.neuro_simulation.entities.Ant;
import com.jeekrs.neuro_simulation.entities.AntNest;
import com.jeekrs.neuro_simulation.entities.Food;
import com.jeekrs.neuro_simulation.populations.Population;

import java.util.HashMap;

public class WorldSystem extends SimpleSystem {

    public HashMap<Class, Population> populations = new HashMap<>();

    @Override
    public void init() {
        populations.put(Food.class, new Population());
        populations.put(Ant.class, new Population());
        populations.put(AntNest.class, new Population() {{
            addNewborn(new AntNest(0, 0));
            addNewborn(new AntNest(-1000, 0));
            addNewborn(new AntNest(1000, 0));
        }});
    }

    public void update(float delta) {

        populations.forEach((k, v) -> v.update());
    }



}
