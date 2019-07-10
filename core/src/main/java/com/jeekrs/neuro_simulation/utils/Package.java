package com.jeekrs.neuro_simulation.utils;

import java.util.ArrayList;
import java.util.List;

public class Package {
    public ArrayList<Double> vals;
    public Package(ArrayList<Double> vals)
    {
        this.vals = vals;
    }
    public Package()
    {
        this.vals = new ArrayList<>();
    }
    public Package(double... vals) {
        this();
        for (double v : vals) {
            this.vals.add(v);
        }
    }

    public Package append(Package b)
    {
        vals.addAll(b.vals);
        return this;
    }

    public Package slice(int i, int length) {
        List<Double> list = vals.subList(i, length + i);
        return new Package(new ArrayList<>(list));
    }
    public double[] getRawArray() {
        double[] d = new double[vals.size()];
        for (int i = 0; i < vals.size(); i++) {
            d[i] = vals.get(i);
        }
        return d;
    }
}
