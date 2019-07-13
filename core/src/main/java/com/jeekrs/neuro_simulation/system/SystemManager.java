package com.jeekrs.neuro_simulation.system;

import com.jeekrs.neuro_simulation.renderers.LivingRenderer;
import com.jeekrs.neuro_simulation.renderers.NestRenderer;
import com.jeekrs.neuro_simulation.renderers.WallRenderer;

import java.util.ArrayList;

public class SystemManager {
    public ArrayList<SimpleSystem> systems = new ArrayList<>();

    public RenderSystem renderSystem = new RenderSystem();
    public InputSystem inputSystem = new InputSystem();
    public EntitySystem entitySystem = new EntitySystem();
    public UISystem UISystem = new UISystem();
    public FightingSystem fightingSystem = new FightingSystem();
    public NestSystem nestSystem = new NestSystem();

    public void init() {
        this.addSystem(inputSystem);
        this.addSystem(entitySystem);
        this.addSystem(renderSystem);
        this.addSystem(UISystem);
        this.addSystem(fightingSystem);
        this.addSystem(nestSystem);
        renderSystem.addRenderer(new LivingRenderer());
        renderSystem.addRenderer(new WallRenderer());
        renderSystem.addRenderer(new NestRenderer());
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
