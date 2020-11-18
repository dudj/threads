package threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * 两个线程交替打印0~100的奇偶数，用wait和notify
 */
public class WaitNotifyPrintOddEvenWait {
    private static int count = 0;
    private static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        new Thread(new TurningRunner(),"偶数").start();
        Thread.sleep(100);
        new Thread(new TurningRunner(),"奇数").start();
    }
    /**
     * 1.拿到锁，我们就打印
     * 2.打印完，唤醒其它线程，自己休眠
     */
    static class TurningRunner implements Runnable{
        @Override
        public void run() {
            while (count<=100){
                synchronized (lock){
                    System.out.println(Thread.currentThread().getName()+":"+count++);
                    lock.notify();
                    //如果任务还没结束，就让出当前的锁，并让自己休眠
                    if(count<=100){
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
