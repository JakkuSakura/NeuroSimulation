package com.jeekrs.neuro_simulation.utils;

import java.util.Collection;
import java.util.Optional;
import java.util.Random;

public class RandomUtil {
    public static final int RAND_SEED = (int) System.currentTimeMillis();
	public static Random rand = new Random (RAND_SEED);

    public static int nextInt() {
        return rand.nextInt();
    }

    public static int nextInt(int a, int b) {
        return (int) nextFloat(a, b - 0.1f);
    }

    public static float nextFloat() {
        return rand.nextFloat();
    }

    public static float nextFloat(float a, float b) {
		if (a > b) {
            float tmp = a;
			a = b;
			b = tmp;
		}
        return rand.nextFloat() * (b - a) + a;
	}
	public static boolean success(float p)
    {
        return nextFloat() < p;
    }
}
