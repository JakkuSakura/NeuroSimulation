package com.jeekrs.neuro_simulation.populations;

import com.jeekrs.neuro_simulation.entities.Entity;

import java.util.ArrayList;
import java.util.TreeSet;

public class Population {
    private TreeSet<Entity> dead = new TreeSet<>();
    private TreeSet<Entity> newborn = new TreeSet<>();

    private ArrayList<Entity> entities = new ArrayList<>();

    public void update() {
        ArrayList<Entity> new_list = new ArrayList<>(newborn);
        entities.forEach(e -> {
            if (!dead.contains(e))
                new_list.add(e);
        });
        entities = new_list;
        dead.clear();
        newborn.clear();
    }

    public void addDead(Entity e) {
        dead.add(e);
    }


    public void addNewborn(Entity e) {
        newborn.add(e);
    }


    public ArrayList<Entity> getEntities() {
        return entities;
    }

}
