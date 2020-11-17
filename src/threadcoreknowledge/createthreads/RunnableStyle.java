package threadcoreknowledge.createthreads;

/**
 * 通过Runnable实现线程
 * 相对于Threads实现 更好
 *
 */
public class RunnableStyle implements Runnable{
    public static void main(String[] args) {
        Thread thread = new Thread(new RunnableStyle());
        thread.start();
    }
    @Override
    public void run() {
        System.out.println("用Runnable方法实现线程");
    }
}
