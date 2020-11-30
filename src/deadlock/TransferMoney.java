package deadlock;

/**
 * 银行转账
 * 带有注释的时候 休眠的时候会发生死锁
 * 用换序来避免转账死锁，demo中使用了hash值，但是一般来说数据库中都有设计主键，主键是唯一的
 */
public class TransferMoney implements Runnable{
    int flag = 1;
    static Account a = new Account(500);
    static Account b = new Account(500);
    static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        TransferMoney o1 = new TransferMoney();
        TransferMoney o2 = new TransferMoney();

        o1.flag = 1;
        o2.flag = 0;

        Thread t1 = new Thread(o1);
        Thread t2 = new Thread(o2);

        t1.start();
        t2.start();

        t1.join();
        t2.join();
        System.out.println("A的余额为：" + a.balance);
        System.out.println("B的余额为：" + b.balance);
    }
    @Override
    public void run() {
        if(flag == 1){
            transferMoney(a, b, 200);
        }
        if (flag == 0){
            transferMoney(b, a, 200);
        }
    }

    public static void transferMoney(Account from, Account to, int money) {
        //java 中每一个对象都有一个hash值 唯一的 但是有可能值一样 几率很小
        class Helper{
            public void transfer(Account from, Account to, int money){
                if (from.balance - money < 0){
                    System.out.println("转账失败，余额不足！");
                    return;
                }
                from.balance -= money;
                to.balance += money;
                System.out.println("转账"+money+"元成功");
            }
        }
        int fromHash = System.identityHashCode(from);
        int toHash = System.identityHashCode(to);
        if(fromHash < toHash){
            synchronized (from){
                synchronized (to) {
                    new Helper().transfer(from, to, money);
                }
            }
        }
        else if(fromHash > toHash){
            synchronized (to){
                synchronized (from) {
                    new Helper().transfer(to, from, money);
                }
            }
        }else {
            //hash冲突，进行人为操作
            synchronized (lock){
                synchronized (to){
                    synchronized (from) {
                        new Helper().transfer(to, from, money);
                    }
                }
            }
        }

    }
    static class Account{
        int balance;

        public Account(int balance) {
            this.balance = balance;
        }

        public int getBalance() {
            return balance;
        }

        public void setBalance(int balance) {
            this.balance = balance;
        }
    }
}