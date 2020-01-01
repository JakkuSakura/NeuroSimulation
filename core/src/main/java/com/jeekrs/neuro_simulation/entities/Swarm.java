package com.jeekrs.neuro_simulation.entities;

import com.jeekrs.neuro_simulation.components.*;

public class Swarm extends Entity {
    public float radius = 32;
    public Swarm(float x, float y) {
        CanEat c1 = new CanEat();
        Position c = new Position(x, y);
        Fighting f = new Fighting() {{
            damage = 10;
            defense = 10;
            health = 100;
            health_limit = 100;
        }};

        putComponent("can_eat", c1);
        putComponent("pos", c);
        putComponent("fight", f);
        putComponent("hittable", new Hittable() {
            @Override
            public boolean contains(float x, float y) {
                return (c.x - x) * (c.x - x) + (c.y - y) * (c.y - y) <= radius * radius;
            }
        });
    }
}
