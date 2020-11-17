package threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * 3个线程，线程1和线程2首先被阻塞，线程3唤醒他们。notify，notifyAll
 * start 先执行不代表线程先启动
 */
public class WaitNotifyAll implements Runnable{
    public static Object resourceA = new Object();
    @Override
    public void run() {
        synchronized (resourceA){
            System.out.println(Thread.currentThread().getName() + "got resourceA lock.");
            try {
                System.out.println(Thread.currentThread().getName() + "waits to start.");
                resourceA.wait();
                System.out.println(Thread.currentThread().getName() + "'s waiting to end.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = new WaitNotifyAll();
        Thread threadA = new Thread(runnable);
        Thread threadB = new Thread(runnable);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (resourceA) {
                    resourceA.notifyAll();
                    //程序无法结束运行，因为还有线程没有结束
//                    resourceA.notify();
                    System.out.println("线程C已经成功notified");
                }
            }
        });
        threadA.start();
        threadB.start();
        //确保释放之前 都启动了 所以加休眠时间
//        Thread.sleep(200);
        thread.start();
    }
}
