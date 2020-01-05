package com.jeekrs.neuro_simulation.systems;


import java.util.ArrayList;

public class UISystem extends SimpleSystem {
    private ArrayList<UIComponent> uiComponents = new ArrayList<>();

    public void addUIComponent(UIComponent uiComponent) {
        addList.add(uiComponent);
    }

    private ArrayList<UIComponent> addList = new ArrayList<>();
    private ArrayList<UIComponent> removeList = new ArrayList<>();

    public void removeUIComponent(UIComponent uiComponent) {
        removeList.add(uiComponent);
    }

    @Override
    public void update(float delta) {
        for (UIComponent uiComponent : uiComponents) {
            uiComponent.update();
        }
        removeList.forEach(e -> {
            uiComponents.remove(e);
            e.dispose();
        });
        removeList.clear();
        addList.forEach(e -> {
            uiComponents.add(e);
            e.init();
        });
        addList.clear();
    }

    @Override
    public void resize(int width, int height) {
        for (UIComponent uiComponent : uiComponents) {
            uiComponent.resize(width, height);
        }
    }

    @Override
    public void dispose() {
        for (UIComponent uiComponent : uiComponents) {
            uiComponent.dispose();
        }
    }

}
