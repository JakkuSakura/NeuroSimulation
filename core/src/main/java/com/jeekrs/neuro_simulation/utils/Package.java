package com.jeekrs.neuro_simulation.utils;

import java.util.ArrayList;
import java.util.List;

public class Package {
    public ArrayList<Float> vals;

    public Package(ArrayList<Float> vals)
    {
        this.vals = vals;
    }
    public Package()
    {
        this.vals = new ArrayList<>();
    }

    public Package(float... vals) {
        this();
        for (float v : vals) {
            this.vals.add(v);
        }
    }

    public Package append(Package b)
    {
        vals.addAll(b.vals);
        return this;
    }

    public Package slice(int i, int length) {
        List<Float> list = vals.subList(i, length + i);
        return new Package(new ArrayList<>(list));
    }

    public float[] getRawArray() {
        float[] d = new float[vals.size()];
        for (int i = 0; i < vals.size(); i++) {
            d[i] = vals.get(i);
        }
        return d;
    }
}
