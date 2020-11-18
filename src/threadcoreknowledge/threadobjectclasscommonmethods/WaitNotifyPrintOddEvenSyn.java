package threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * 两个线程交替打印0~100的奇偶数，用synchronized关键字来实现
 * 注意的是：这种方法可以实现，但是消耗资源，因为这一个锁不一定被谁拿到
 */
public class WaitNotifyPrintOddEvenSyn {
    private static int count;
    private static final Object lock = new Object();
    /**
     * 1.建立两个线程 一个是只处理偶数 另一个只处理奇数(用位运算)
     * 2.兄弟线程的一个进度 用synchronized来通信
     */
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(count<100){
                    synchronized (lock){
                        if ((count&1)==0){
                            System.out.println(Thread.currentThread().getName()+"打印："+count++);
                        }
                    }
                }
            }
        },"偶数").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(count<100){
                    synchronized (lock){
                        if ((count&1)==1){
                            System.out.println(Thread.currentThread().getName()+"打印："+count++);
                        }
                    }
                }
            }
        },"奇数").start();
    }
}
