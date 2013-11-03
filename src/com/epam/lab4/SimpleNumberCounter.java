package com.epam.lab4;

public class SimpleNumberCounter {
    private volatile long count;

    public SimpleNumberCounter(long i)
    {
        count = i;
    }

    public long getAndIncrement()
    {
        return count++;
    }

}
