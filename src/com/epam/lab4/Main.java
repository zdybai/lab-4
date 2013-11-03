package com.epam.lab4;

/**
 * Created with IntelliJ IDEA.
 * User: Zdybai
 * Date: 04.11.13
 * Time: 0:35
 * To change this template use File | Settings | File Templates.
 */

public class Main {
    public static void main(String[] args){

        SimpleNumberCounter counter = new SimpleNumberCounter(1);

        Thread[] thread = new Thread[LockingUtils.THREADS_AMOUNT];

        for(int i = 0; i < LockingUtils.THREADS_AMOUNT; ++i)
        {
            thread[i] = new Thread(new SimpleNumberCounterRunnable(i, counter));
            thread[i].start();
        }

        for(int i = 0; i < LockingUtils.THREADS_AMOUNT; ++i)
        {
            try {
                thread[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
