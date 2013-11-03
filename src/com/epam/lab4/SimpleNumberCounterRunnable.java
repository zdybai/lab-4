package com.epam.lab4;

public class SimpleNumberCounterRunnable implements Runnable {
    private final SimpleNumberCounter counter;
    private final int threadId;
    private final long limitForSimpleNumber = 1234567;

    public SimpleNumberCounterRunnable(int threadId, SimpleNumberCounter counter) {
        this.counter = counter;
        this.threadId = threadId;
    }

    @Override
    public void run() {

        long i = 0;
        while (i < limitForSimpleNumber)
        {
            LockingUtils.lockVar.lock(threadId);
            try{
                i = counter.getAndIncrement();
            } finally {
                LockingUtils.lockVar.unlock(threadId);
            }
            if(isPrime(i))
                System.out.println("Thread " + threadId + " generated prime number: " + i);
        }

    }

    private boolean isPrime(long a)
    {
        long n = (long)Math.sqrt(a);
        for(long i = 2; i <= n; ++i)
            if(a % i == 0) return false;
        return true;
    }
}