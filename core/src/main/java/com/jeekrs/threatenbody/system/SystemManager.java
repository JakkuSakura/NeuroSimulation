package com.jeekrs.threatenbody.system;

import java.util.ArrayList;

public class SystemManager {
    public ArrayList<SimpleSystem> systems = new ArrayList<>();
    public PhysicsSystem physicsSystem = new PhysicsSystem();
    public RenderSystem renderSystem = new RenderSystem();
    public InputSystem inputSystem = new InputSystem();
    public World world;

    public SystemManager() {

    }


    public void init() {
        this.addSystem(inputSystem);
        this.addSystem(physicsSystem);
        this.addSystem(renderSystem);

        systems.forEach(SimpleSystem::init);
    }

    public void update(float delta) {
        systems.forEach(e -> e.update(delta));
    }

    public void addSystem(SimpleSystem system) {
        systems.add(system);
        system.setSystemManager(this);
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public void dispose() {
        systems.forEach(SimpleSystem::dispose);
    }
}
