package com.jeekrs.neuro_simulation.components.abilities;

import com.jeekrs.neuro_simulation.components.Component;
import com.jeekrs.neuro_simulation.entities.Entity;

public class Fighting extends Component {
    public float damage;
    public float defense;
    public float energy;
    public float energy_limit;
    public float health;
    public float health_limit;
    public void adjustHealth() {
        if (health < 0)
            health = 0;
        if (health > health_limit)
            health = health_limit;
    }
    public void adjustEnergy() {
        if (energy < 0)
            energy = 0;
        if (energy > energy_limit)
            energy = energy_limit;
    }

    public static boolean hasFighting(Entity entity) {
        return entity.hasComponentByNameAndClass("fighting", Fighting.class);
    }

    public static Fighting getFighting(Entity entity) {
        return entity.getComponentByNameAndClass("fighting", Fighting.class);
    }

}
