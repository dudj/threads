package deadlock;

/**
 * 银行转账
 * 带有注释的时候 休眠的时候会发生死锁
 */
public class TransferMoney implements Runnable{
    int flag = 1;
    static Account a = new Account(500);
    static Account b = new Account(500);

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
        synchronized (from){
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            synchronized (to){
                if (from.balance - money < 0){
                    System.out.println("转账失败，余额不足！");
                    return;
                }
                from.balance -= money;
                to.balance += money;
                System.out.println("转账"+money+"元成功");
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