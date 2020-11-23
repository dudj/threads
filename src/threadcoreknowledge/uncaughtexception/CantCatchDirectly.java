package threadcoreknowledge.uncaughtexception;

/**
 * 1.不加try catch 抛出4个异常，都带线程名字
 * 2.加了 try catch，期望捕获到第一个线程的异常，线程324不应该运行，希望看到打印出Caught Exception
 * 3. 执行时发现，根本没有Caught Exception，线程234依然运行并且抛出异常
 *
 * 说明线程的异常不能用传统的方法捕获
 */
public class CantCatchDirectly implements Runnable{
    public static void main(String[] args) throws InterruptedException {
        //try catch 只能捕获线程内的异常
        try{
            new Thread(new CantCatchDirectly(),"myThread-1").start();
            Thread.sleep(300);
            new Thread(new CantCatchDirectly(),"myThread-2").start();
            Thread.sleep(300);
            new Thread(new CantCatchDirectly(),"myThread-3").start();
            Thread.sleep(300);
            new Thread(new CantCatchDirectly(),"myThread-4").start();
        }catch (RuntimeException e){
            System.out.println("Caught Exception.");
        }
    }
    @Override
    public void run() {
        try{
            throw new RuntimeException();
        }catch (RuntimeException e){
            System.out.println("Caught Exception.");
        }
    }
}
