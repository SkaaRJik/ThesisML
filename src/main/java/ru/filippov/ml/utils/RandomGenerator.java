package ru.filippov.ml.utils;

import java.util.Random;

public class RandomGenerator extends Random {

    @Override
    public float nextFloat() {
        return super.nextFloat() * (1.0f - 0.0f) + 0.0f;
    }

    @Override
    public double nextDouble() {
        return super.nextDouble() * (1.0 - 0.0) + 0.0;
    }
}
