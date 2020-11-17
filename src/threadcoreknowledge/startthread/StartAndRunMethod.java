package threadcoreknowledge.startthread;

/**
 * 对比 start和run两种启动线程的方式
 */
public class StartAndRunMethod {
    public static void main(String[] args) {
        Runnable runnable = () -> {
            System.out.println(Thread.currentThread().getName());
        };
        //由主线程去启动
        runnable.run();

        Thread thread = new Thread(runnable);
        thread.start();
    }
}
