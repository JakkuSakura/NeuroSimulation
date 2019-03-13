package com.jeekrs.threatenbody.component;

import com.jeekrs.threatenbody.interfaces.Attachable;

import java.util.TreeMap;

public class AttachableCollection implements Component {
    public TreeMap<String, Attachable> components = new TreeMap<>();

    public AttachableCollection() {

    }

    public void addComponent(String name, Attachable component) {
        components.put(name, component);
    }
    public void addComponent(Attachable component) {
        components.put(component.toString(), component);
    }
}
