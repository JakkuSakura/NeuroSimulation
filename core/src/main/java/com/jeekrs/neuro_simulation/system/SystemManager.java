package com.jeekrs.neuro_simulation.system;

import com.jeekrs.neuro_simulation.renderers.*;

import java.util.ArrayList;

public class SystemManager {
    public ArrayList<SimpleSystem> systems = new ArrayList<>();

    public RenderSystem renderSystem = new RenderSystem();
    public InputSystem inputSystem = new InputSystem();
    public EntitySystem entitySystem = new EntitySystem();
    public UISystem UISystem = new UISystem();
    public FightingSystem fightingSystem = new FightingSystem();
    public NestSystem nestSystem = new NestSystem();
    public ResourceSystem resourceSystem = new ResourceSystem();
    public AgendaSystem agendaSystem = new AgendaSystem();

    public void init() {
        addSystem(inputSystem);
        addSystem(entitySystem);
        addSystem(renderSystem);
        addSystem(UISystem);
        addSystem(fightingSystem);
        addSystem(nestSystem);
        addSystem(resourceSystem);
        addSystem(agendaSystem);

        renderSystem.addRenderer(new NestRenderer());
        renderSystem.addRenderer(new WallRenderer());
        renderSystem.addRenderer(new WoodRenderer());
        renderSystem.addRenderer(new LivingRenderer());
        renderSystem.addRenderer(new FoodRenderer());
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
