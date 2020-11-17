package threadcoreknowledge.stopthreads.volatiledemo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 用中断来修复
 * 弥补volatile 阻塞后无法唤醒的缺陷
 */
public class WrongWayVolatileFixed {
    public static void main(String[] args) throws InterruptedException {
        WrongWayVolatileFixed wrongWayVolatileFixed = new WrongWayVolatileFixed();
        ArrayBlockingQueue storage = new ArrayBlockingQueue(10);
        Producer producer = wrongWayVolatileFixed.new Producer(storage);
        Thread producerThread = new Thread(producer);
        producerThread.start();
        Thread.sleep(1000);

        Consumer consumer = wrongWayVolatileFixed.new Consumer(storage);
        while (consumer.needMoreNums()){
            System.out.println(consumer.storage.take()+"：被消费了");
            Thread.sleep(100);
        }
        System.out.println("消费者不需要更多数据了。");
        //一旦消费不需要更多数据了，我们应该让生产者停下来，但是实际情况 并没有
        producerThread.interrupt();
    }
    class Producer implements Runnable{
        BlockingQueue storage;

        public Producer(BlockingQueue storage) {
            this.storage = storage;
        }

        @Override
        public void run() {
            int num = 0;
            try {
                while (num<10000 && !Thread.currentThread().isInterrupted()){
                    if(num%100 == 0){
                        storage.put(num);
                        System.out.println(num+"：是100的倍数");
                    }
                    num ++;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                System.out.println("生产者停止运行");
            }
        }
    }

    class Consumer{
        BlockingQueue storage;

        public Consumer(BlockingQueue storage) {
            this.storage = storage;
        }
        public boolean needMoreNums(){
            if (Math.random() > 0.95){
                return false;
            }
            return true;
        }
    }
}