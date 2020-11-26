package jmm;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * volatile适用的情况2
 * 如果不是被各个线程赋值，而是做了其他操作，比如：对比，计算，那就不适用了
 */
public class NoVolatile2 implements Runnable{
    volatile boolean done = false;
    AtomicInteger realA = new AtomicInteger();

    public static void main(String[] args) throws InterruptedException {
        NoVolatile2 useVolatile = new NoVolatile2();
        Thread thread = new Thread(useVolatile);
        Thread thread1 = new Thread(useVolatile);

        thread.start();
        thread1.start();

        thread.join();
        thread1.join();
        System.out.println(useVolatile.done);
        System.out.println(useVolatile.realA.get());
    }
    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            flipDone();
            realA.incrementAndGet();
        }
    }

    private void flipDone() {
        done = !done;
    }
}
