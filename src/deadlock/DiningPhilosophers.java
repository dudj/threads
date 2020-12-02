package deadlock;

/**
 * 哲学家就餐问题
 * 改变一个哲学家拿筷子的顺序(避免策略)
 */
public class DiningPhilosophers {

    public static void main(String[] args) {
        //初始化 多少个哲学家
        Philosopher[] philosophers = new Philosopher[5];
        //几双筷子
        Object[] chopsticks = new Object[philosophers.length];
        //赋值
        for (int i = 0; i < chopsticks.length; i++) {
            chopsticks[i] = new Object();
        }
        for (int i = 0; i < philosophers.length; i++) {
            //左筷子 从0开始 右筷子 从1开始
            Object leftChopstick = chopsticks[i];
            Object rightChopstick = chopsticks[(i + 1) % chopsticks.length];
            if(i == chopsticks.length - 1){
                philosophers[i] = new Philosopher(rightChopstick, leftChopstick);
            }else{
                philosophers[i] = new Philosopher(leftChopstick, rightChopstick);
            }
            new Thread(philosophers[i], "哲学家" + (i+1) + "号").start();
        }
    }

    /**
     * 哲学家 线程类的逻辑
     */
    public static class Philosopher implements Runnable{
        private Object leftChopstick;
        private Object rightChopstick;

        public Philosopher(Object leftChopstick, Object rightChopstick) {
            this.leftChopstick = leftChopstick;
            this.rightChopstick = rightChopstick;
        }
        @Override
        public void run() {
            //哲学家 要思考 然后 拿起左边的筷子 然后拿起右边的筷子 吃饭 放下左边的筷子 放下右边的筷子
            try{
                while(true){
                    doAction("Thinking");
                    synchronized (leftChopstick){
                        doAction("picked up left chopsticks");
                        synchronized (rightChopstick){
                            doAction("picked up right chopsticks - eating");
                            doAction("put down right chopsticks");
                        }
                        doAction("put down left chopsticks");
                    }
                }
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        private void doAction(String action) throws InterruptedException {
            System.out.println(Thread.currentThread().getName() + ":" + action);
            Thread.sleep((long) (Math.random() * 10));
        }
    }
}
