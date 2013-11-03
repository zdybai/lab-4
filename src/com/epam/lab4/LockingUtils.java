package com.epam.lab4;

public class LockingUtils {
    public static final int THREADS_AMOUNT = 2;
    public static LamportsBakeryAlgorithm lockVar;

    static {
        lockVar = new LamportsBakeryAlgorithm(THREADS_AMOUNT);
    }
}
