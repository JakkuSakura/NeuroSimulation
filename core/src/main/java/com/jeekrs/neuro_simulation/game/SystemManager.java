package com.jeekrs.neuro_simulation.game;

import com.jeekrs.neuro_simulation.inputs.InputSystem;
import com.jeekrs.neuro_simulation.UI.UISystem;
import com.jeekrs.neuro_simulation.displays.RenderSystem;

import java.util.ArrayList;

public class SystemManager {
    public ArrayList<SimpleSystem> systems = new ArrayList<>();

    public RenderSystem renderSystem = new RenderSystem();
    public InputSystem inputSystem = new InputSystem();
    public WorldSystem worldSystem = new WorldSystem();
    public com.jeekrs.neuro_simulation.UI.UISystem UISystem = new UISystem();
    public FightingSystem fightingSystem = new FightingSystem();
    public FoodSystem foodSystem = new FoodSystem();
    public NestSystem nestSystem = new NestSystem();
    public NeuralSystem neuralSystem = new NeuralSystem();

    public void init() {
        addSystem(inputSystem);
        addSystem(renderSystem);
        addSystem(UISystem);
        addSystem(fightingSystem);
        addSystem(neuralSystem);
        addSystem(foodSystem);
        addSystem(nestSystem);

        addSystem(worldSystem);
        systems.forEach(SimpleSystem::init);

    }

    public void update(float delta) {
        systems.forEach(e -> e.update(delta * 10));
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
