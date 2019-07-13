package com.jeekrs.neuro_simulation.processors.sensories;

import com.jeekrs.neuro_simulation.GameScreen;
import com.jeekrs.neuro_simulation.entities.Entity;
import com.jeekrs.neuro_simulation.entities.Wall;
import com.jeekrs.neuro_simulation.entities.livings.Living;
import com.jeekrs.neuro_simulation.utils.Package;

public class NearbyWallSensory implements Sensory {

    @Override
    public Package detect(Living living) {
        if (GameScreen.systemManager == null)
            return new Package(0);

        float i = 0, x = 0, y = 0;
        for (Entity entity : GameScreen.systemManager.entitySystem.entities) {
            if (entity instanceof Wall) {
                float dst = entity.getPos().dst(living.getPos());
                i += .1;
                x += (entity.getPos().x - living.getPos().x) / (dst + 1) * 100;
                y += (entity.getPos().y - living.getPos().y) / (dst + 1) * 100;
            }
        }
        float tot = Math.abs(x) + Math.abs(y) + 1e-5f;
        x /= tot;
        y /= tot;
        return new Package(i, x, y);
    }

    @Override
    public Sensory clone() {
        try {
            return (Sensory) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
