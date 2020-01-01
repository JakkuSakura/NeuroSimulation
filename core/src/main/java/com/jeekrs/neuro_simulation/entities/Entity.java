package com.jeekrs.neuro_simulation.entities;

import com.jeekrs.neuro_simulation.components.Component;
import com.sun.istack.internal.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Entity implements Comparable<Entity>, Cloneable {
    private HashMap<String, Component> components = new HashMap<>();

    public Component getComponentByName(String string) {
        return components.get(string);
    }

    @SuppressWarnings("unchecked")
    public <T extends Component> T getComponentByNameAndCast(String string) {
        return (T)components.get(string);
    }
    public <T extends Component> T getComponentByNameAndClass(String string, Class<T> clazz) {
        return clazz.cast(components.get(string));
    }


    public <T extends Component> T getComponentByClass(Class<T> clazz) {
        for (Map.Entry<String, Component> entry : components.entrySet()) {
            Component v = entry.getValue();
            if (clazz.isAssignableFrom(v.getClass()))
                // checked above
                // noinspection unchecked
                return (T) v;
        }
        return null;
    }

    public void putComponent(String s, Component c) {
        components.put(s, c);
    }

    public void removeComponentByName(String s) {
        components.remove(s);
    }

    public void removeComponent(Component c) {
        String name = null;
        for (Map.Entry<String, Component> pr: components.entrySet()) {
            // by address
            if(pr.getValue() == c)
                name = pr.getKey();
        }
        if (name == null)
            throw new RuntimeException("No such component of " + c);
        components.remove(name);
    }

    @NotNull
    public <T extends Component> ArrayList<T> getComponentListByClass(Class<T> clazz) {
        ArrayList<T> list = new ArrayList<>();
        for (Map.Entry<String, Component> entry : components.entrySet()) {
            Component v = entry.getValue();
            if (clazz.isAssignableFrom(v.getClass()))
                // checked above
                // noinspection unchecked
                list.add((T) v);
        }
        return list;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

    @Override
    public int compareTo(Entity e) {
        return Integer.compare(hashCode(), e.hashCode());
    }

    @SuppressWarnings("unchecked")
    @Override
    public Entity clone() {
        try {
            Entity clone = (Entity) super.clone();
            clone.components = (HashMap<String, Component>) components.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }



    public <T extends Component> boolean hasComponentByClass(Class<T> clazz) {
        return getComponentByClass(clazz) != null;
    }
    public <T extends Component> boolean hasComponentByName(String name) {
        return getComponentByName(name) != null;
    }
}




