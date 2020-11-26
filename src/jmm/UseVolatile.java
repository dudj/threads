package jmm;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * volatile适用的情况1
 */
public class UseVolatile  implements Runnable{
    volatile boolean done = false;
    AtomicInteger realA = new AtomicInteger();

    public static void main(String[] args) throws InterruptedException {
        UseVolatile useVolatile = new UseVolatile();
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
            setDone();
            realA.incrementAndGet();
        }
    }
    //不取决于之前的赋值，就给一个定值(自始至终都是被各个线程赋值的，没有其他操作)
    private void setDone() {
        done = true;
    }
}
