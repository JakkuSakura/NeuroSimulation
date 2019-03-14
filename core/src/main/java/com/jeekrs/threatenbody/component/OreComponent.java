package com.jeekrs.threatenbody.component;

import java.util.Map;
import java.util.TreeMap;

public class OreComponent implements Component {
    public Map<OreName, Integer> ores = new TreeMap<>();
    static public OreComponent merge(OreComponent a, OreComponent b)
    {
        OreComponent ores = new OreComponent();
        a.ores.forEach((k, v) -> ores.ores.put(k, v));
        b.ores.forEach((k, v) -> {
            if (ores.ores.containsKey(k))
                ores.ores.put(k, ores.ores.get(k) + v);
            else
                ores.ores.put(k, v);

        });
        return ores;
    }
}
