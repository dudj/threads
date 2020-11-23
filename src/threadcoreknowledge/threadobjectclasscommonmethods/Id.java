package threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * 线程ID从1开始，JVm运行起来之后，我们自己创建的线程的ID早已不是2
 * ++threadSeqNumber; 默认先++ 默认是1(主线程)
 * 默认系统会 有多个线程，gc相关的，系统相关的等等
 */
public class Id {
    public static void main(String[] args) {
        Thread thread = new Thread();
        System.out.println("主线程的ID："+Thread.currentThread().getId());
        System.out.println("子线程的ID："+thread.getId());
    }
}
