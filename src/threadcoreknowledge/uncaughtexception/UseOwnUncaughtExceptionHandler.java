package threadcoreknowledge.uncaughtexception;

/**
 * 使用自己写的UncaughtExceptionHandler
 */
public class UseOwnUncaughtExceptionHandler implements Runnable{
    public static void main(String[] args) throws InterruptedException {
        Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler("捕获器1"));
        new Thread(new UseOwnUncaughtExceptionHandler(),"myThread-1").start();
        Thread.sleep(300);
        new Thread(new UseOwnUncaughtExceptionHandler(),"myThread-2").start();
        Thread.sleep(300);
        new Thread(new UseOwnUncaughtExceptionHandler(),"myThread-3").start();
        Thread.sleep(300);
        new Thread(new UseOwnUncaughtExceptionHandler(),"myThread-4").start();
    }
    @Override
    public void run() {
        throw new RuntimeException();
    }
}
