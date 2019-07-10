package com.jeekrs.neuro_simulation.sensories;

import com.jeekrs.neuro_simulation.entities.Entity;
import com.jeekrs.neuro_simulation.entities.Living;
import com.jeekrs.neuro_simulation.system.SystemManager;
import com.jeekrs.neuro_simulation.system.WorldSystem;
import com.jeekrs.neuro_simulation.utils.Package;


public class NearbyLivingSensory implements Sensory {

    @Override
    public Package detect(Living living) {
        if (SystemManager.systemManager == null)
            return new Package(0);
        WorldSystem worldSystem = SystemManager.systemManager.worldSystem;
        float i = 0, x = 0, y = 0;
        for (Entity entity : worldSystem.entities) {
            if (entity instanceof Living) {
                float dst = entity.getPos().dst(living.getPos());
                if (dst < 200) {
                    i += .1;
                    x += (entity.getPos().x - living.getPos().x) / (dst + 1) * 100;
                    y += (entity.getPos().y - living.getPos().y) / (dst + 1) * 100;
                }
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


