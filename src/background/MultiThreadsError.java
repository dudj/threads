package background;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 第一种：运行结果出错
 * 演示计数不准确(减少)，找出具体出错的位置
 */
public class MultiThreadsError implements  Runnable{
    int index = 0;
    static AtomicInteger releIndex = new AtomicInteger();
    static AtomicInteger wrongCount = new AtomicInteger();
    //2 代表的是几个线程
    static volatile CyclicBarrier cyclicBarrier1 = new CyclicBarrier(2);
    static volatile CyclicBarrier cyclicBarrier2 = new CyclicBarrier(2);
    static MultiThreadsError instance = new MultiThreadsError();
    final boolean[] marked = new boolean[1000000];

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(instance);
        Thread thread2 = new Thread(instance);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("表面上结果："+instance.index);
        System.out.println("真正运行的次数："+releIndex.get());
        System.out.println("错误次数："+wrongCount.get());
    }

    @Override
    public void run() {
        marked[0] = true;
        /*while (index < 10000){
            index++;
        }*/
        for (int i = 0; i < 10000; i++) {
            //index 会被篡改 使用cyclicBarrier1 一起等待
            try {
                cyclicBarrier2.reset();
                cyclicBarrier1.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            index++;
            try {
                cyclicBarrier1.reset();
                cyclicBarrier2.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            releIndex.incrementAndGet();
            //检查是否存在线程安全问题 同时到达 无法捕捉
            synchronized (instance){
                if (marked[index] && marked[index-1]){
                    wrongCount.incrementAndGet();
                    System.out.println("发生错误：" + index);
                }
                marked[index] = true;
            }
        }
    }
}
