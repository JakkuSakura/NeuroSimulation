package com.jeekrs.neuro_simulation.system;

import java.util.ArrayList;

public class SystemManager {
    public ArrayList<SimpleSystem> systems = new ArrayList<>();

    public RenderSystem renderSystem = new RenderSystem();
    public InputSystem inputSystem = new InputSystem();
    public EntitySystem entitySystem = new EntitySystem();
    public UISystem UISystem = new UISystem();
    public FightingSystem fightingSystem = new FightingSystem();
    public NestSystem nestSystem = new NestSystem();
    public AgendaSystem agendaSystem = new AgendaSystem();
    public ResourceSystem resourceSystem = new ResourceSystem();
    public WorldSystem worldSystem = new WorldSystem();

    public void init() {
        addSystem(inputSystem);
        addSystem(entitySystem);
        addSystem(renderSystem);
        addSystem(UISystem);
        addSystem(fightingSystem);
        addSystem(nestSystem);
        addSystem(agendaSystem);
        addSystem(resourceSystem);
        addSystem(worldSystem);

        systems.forEach(SimpleSystem::init);

    }

    public void newGame() {
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
