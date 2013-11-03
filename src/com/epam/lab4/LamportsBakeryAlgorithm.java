package com.epam.lab4;

import java.util.concurrent.atomic.*;

public class LamportsBakeryAlgorithm {
    private AtomicIntegerArray flag;
    private AtomicIntegerArray label;
    private final int n;

    public LamportsBakeryAlgorithm(int n)
    {
        flag = new AtomicIntegerArray(n);
        label = new AtomicIntegerArray(n);
        this.n = n;

    }

    public void lock(int threadId)
    {
        flag.set(threadId, 1);
        label.set(threadId, max(label) + 1);
        flag.set(threadId, 0);

        for(int i = 0; i < n; ++i)
        {
            if(i != threadId)
            {
                while (flag.get(i) != 0) {}
                while (label.get(i) != 0 && (label.get(i) < label.get(threadId) ||
                        (label.get(i) == label.get(threadId) && threadId > i))) {}
            }
        }
    }

    public void unlock(int threadId)
    {
        label.set(threadId, 0);
    }


    private int max(AtomicIntegerArray array)
    {
        int n = array.length();
        AtomicInteger max = new AtomicInteger();
        max.set(0);

        for(int i = 0; i < n; ++i)
        {
            if(max.get() < array.get(i)) max.set(array.get(i));
        }

        return max.get();
    }
}