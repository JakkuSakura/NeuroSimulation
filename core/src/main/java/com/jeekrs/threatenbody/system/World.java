package com.jeekrs.threatenbody.system;

import com.jeekrs.threatenbody.entity.Entity;

import java.util.LinkedList;

public class World extends SimpleSystem {
    public LinkedList<Entity> entities = new LinkedList<>();

    public World() {

    }
}
