package com.jeekrs.neuro_simulation.utils;

import com.jeekrs.neuro_simulation.interfaces.MyCloneable;

import java.util.ArrayList;

public class Cloner {

    static public float[] copyArray(float[] array) {
        return array.clone();
    }

    static public float[][] copyArray(float[][] array) {
        float[][] a = array.clone();
        for (int i = 0; i < array.length; i++) {
            a[i] = copyArray(array[i]);
        }
        return a;
    }

    static public float[][][] copyArray(float[][][] array) {
        float[][][] a = array.clone();
        for (int i = 0; i < array.length; i++) {
            a[i] = copyArray(array[i]);
        }
        return a;
    }

    static public float[][][][] copyArray(float[][][][] array) {
        float[][][][] a = array.clone();
        for (int i = 0; i < array.length; i++) {
            a[i] = copyArray(array[i]);
        }
        return a;
    }

    public static <T extends MyCloneable> ArrayList<T> deepCopy(ArrayList<T> src) {
        ArrayList<T> l = new ArrayList<>();
        src.forEach(e -> l.add((T) e.clone()));
        return l;
    }

}
