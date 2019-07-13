package com.jeekrs.neuro_simulation.system;

import com.jeekrs.neuro_simulation.entities.Entity;
import com.jeekrs.neuro_simulation.interfaces.Fightable;
import com.jeekrs.neuro_simulation.utils.RandomUtil;

import static com.jeekrs.neuro_simulation.GameScreen.systemManager;

public class FightingSystem extends SimpleSystem {
    @Override
    public void update(float delta) {
        for (Entity entity : systemManager.entitySystem.entities) {
            if (entity instanceof Fightable) {
                Fightable living = (Fightable) entity;
                for (Entity e2 : systemManager.entitySystem.entities) {
                    if (e2 instanceof Fightable) {
                        Fightable living2 = (Fightable) e2;
                        if (living.getPos().dst2(living2.getPos()) < 2500)
                            if (!living.getAgenda().isFriendly(living2.getAgenda())) {
                                float damage = calcDamage(living, living2);
                                living2.setHealth(living2.getHealth() - damage);
                                break;
                            }
                    }
                }
            }
        }
    }

    public float calcDamage(Fightable a, Fightable b) {
        float random = RandomUtil.nextFloat();
        float random2 = RandomUtil.nextFloat();
        return a.getDamage() + Math.max(a.getDamage() - b.getDefence(), 0) * random - Math.max(b.getDefence() - a.getDamage(), 0) * random2;
    }
}
