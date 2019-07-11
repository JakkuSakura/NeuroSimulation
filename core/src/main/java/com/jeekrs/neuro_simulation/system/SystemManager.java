package com.jeekrs.neuro_simulation.system;

import java.util.ArrayList;

public class SystemManager {
    public ArrayList<SimpleSystem> systems = new ArrayList<>();

    public RenderSystem renderSystem = new RenderSystem();
    public InputSystem inputSystem = new InputSystem();
    public EntitySystem entitySystem = new EntitySystem();
    public UISystem UISystem = new UISystem();


    public void init() {
        this.addSystem(inputSystem);
        this.addSystem(entitySystem);
        this.addSystem(renderSystem);
        this.addSystem(UISystem);

        renderSystem.addRenderer(new LivingRenderer());
        renderSystem.addRenderer(new WallRenderer());
        systems.forEach(SimpleSystem::init);
    }

    public void update(float delta) {
        systems.forEach(e -> e.update(delta));
    }

    public void addSystem(SimpleSystem system) {
        systems.add(system);
    }

    public void resize(int width, int height) {
        systems.forEach(e -> e.resize(width, height));
    }

    public void dispose() {
        systems.forEach(SimpleSystem::dispose);
    }

}
