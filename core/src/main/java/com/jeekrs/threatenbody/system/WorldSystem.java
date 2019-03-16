package com.jeekrs.threatenbody.system;

import com.jeekrs.threatenbody.entity.Entity;

import java.util.LinkedList;
import java.util.TreeSet;

public class WorldSystem extends SimpleSystem {
    public TreeSet<Entity> entities = new TreeSet<>();

    public WorldSystem() {

    }
}
