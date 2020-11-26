package jmm;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 不适用volatile a++
 */
public class NoVolatile implements Runnable{
    volatile int a;
    AtomicInteger realA = new AtomicInteger();

    public static void main(String[] args) throws InterruptedException {
        NoVolatile noVolatile = new NoVolatile();
        Thread thread = new Thread(noVolatile);
        Thread thread1 = new Thread(noVolatile);

        thread.start();
        thread1.start();

        thread.join();
        thread1.join();
        System.out.println(noVolatile.a);
        System.out.println(noVolatile.realA.get());
    }
    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            a++;
            realA.incrementAndGet();
        }
    }
}
