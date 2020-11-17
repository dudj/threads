package threadcoreknowledge.sixstates;

/**
 * 展示 Blocked，Waiting，Timed Waiting
 */
public class BlockedWaitingTimedWaiting implements Runnable{
    public static void main(String[] args) throws InterruptedException {
        BlockedWaitingTimedWaiting runnable = new BlockedWaitingTimedWaiting();
        Thread thread1 = new Thread(runnable);
        thread1.start();
        Thread.sleep(10);
        Thread thread2 = new Thread(runnable);
        thread2.start();

        //Time Waiting 因为正在执行的Thread.sleep(1000)
        System.out.println("线程1的状态："+thread1.getState());
        //Blocked 因为thread2想得到syn()的锁 却拿不到 因为线程1没有释放
        System.out.println("线程2的状态："+thread2.getState());
        Thread.sleep(1500);
        //打印出Waiting
        System.out.println("线程1的状态："+thread1.getState());
    }

    @Override
    public void run() {
        syn();
    }
    private synchronized void syn(){
        try {
            Thread.sleep(1000);
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
