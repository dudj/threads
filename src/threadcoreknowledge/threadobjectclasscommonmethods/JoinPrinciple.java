package threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * 通过讲解join的原理，分析出join的代替方法
 * synchronized (thread){
 *  thread.wait();
 * }
 */
public class JoinPrinciple {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "执行完毕");
            }
        });
        thread.start();
        System.out.println("开始等待子线程运行完毕");
//        thread.join();
        //thread类中run方法全部执行完毕之后，主线程会回到这里
        synchronized (thread){
            thread.wait();
        }
        System.out.println("所有子线程运行完毕");
    }
}
