package com.jeekrs.neuro_simulation.utils;

import com.jeekrs.neuro_simulation.interfaces.PublicClonable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Cloner {

    static public <T extends PublicClonable<T>> T[] copyArray(T[] array) {
        T[] clone = array.clone();
        for (int i = 0; i < array.length; i++) {
            clone[i] = array[i].clone();
        }
        return clone;
    }

    static public <T extends PublicClonable<T>> T[][] copyArray(T[][] array) {
        T[][] a = array.clone();
        for (int i = 0; i < array.length; i++) {
            a[i] = copyArray(array[i]);
        }
        return a;
    }

    static public <T extends PublicClonable<T>> T[][][] copyArray(T[][][] array) {
        T[][][] a = array.clone();
        for (int i = 0; i < array.length; i++) {
            a[i] = copyArray(array[i]);
        }
        return a;
    }

    static public <T extends PublicClonable<T>> T[][][][] copyArray(T[][][][] array) {
        T[][][][] a = array.clone();
        for (int i = 0; i < array.length; i++) {
            a[i] = copyArray(array[i]);
        }
        return a;
    }

    public static <T extends PublicClonable<T>> ArrayList<T> deepCopy(ArrayList<T> src) {
        ArrayList<T> l = new ArrayList<>();
        src.forEach(e -> l.add(e.clone()));
        return l;
    }

    public static <V extends PublicClonable<V>> HashMap<String, V> deepCopy(HashMap<String, V> src) {
        HashMap<String, V> clone = new HashMap<>();
        src.forEach((k, v) -> clone.put(k, v.clone()));
        return clone;
    }

}
