package threadcoreknowledge.createthreads.wrongways;

/**
 * Lambda 表达式 实现线程
 */
public class Lambda {
    public static void main(String[] args) {
        new Thread(() -> System.out.println(Thread.currentThread().getName())).start();
    }
}
