package deadlock;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 用tryLock来避免死锁
 */
public class TryLockDeadlock implements Runnable{
    int flag = 1;
    static Lock lock1 = new ReentrantLock();
    static Lock lock2 = new ReentrantLock();

    public static void main(String[] args) {
        TryLockDeadlock deadlock1 = new TryLockDeadlock();
        TryLockDeadlock deadlock2 = new TryLockDeadlock();
        deadlock1.flag = 1;
        deadlock1.flag = 0;
        new Thread(deadlock1).start();
        new Thread(deadlock2).start();
    }
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if(flag == 1){
                try {
                    if(lock1.tryLock(300, TimeUnit.MILLISECONDS)){
                        System.out.println("线程1获取到了锁1");
                        Thread.sleep(new Random().nextInt(10));
                        if(lock2.tryLock(300, TimeUnit.MILLISECONDS)){
                            System.out.println("线程1获取到了锁2");
                            lock1.unlock();
                            lock2.unlock();
                            break;
                        }else{
                            System.out.println("线程1获取第二把锁的时候，失败，已经重试");
                            lock1.unlock();
                            Thread.sleep(new Random().nextInt(10));
                        }
                    }else{
                        System.out.println("线程1获取锁1失败，已经重试");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if(flag == 0){
                try {
                    if(lock2.tryLock(3000, TimeUnit.MILLISECONDS)){
                        System.out.println("线程2获取到了锁2");
                        Thread.sleep(new Random().nextInt(1000));
                        if(lock1.tryLock(3000, TimeUnit.MILLISECONDS)){
                            System.out.println("线程2获取到了锁1");
                            lock2.unlock();
                            lock1.unlock();
                            break;
                        }else{
                            System.out.println("线程2获取第1把锁的时候，失败，已经重试");
                            lock2.unlock();
                            Thread.sleep(new Random().nextInt(1000));
                        }
                    }else{
                        System.out.println("线程2获取锁2失败，已经重试");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
