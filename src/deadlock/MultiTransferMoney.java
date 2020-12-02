package deadlock;

import java.util.Random;
import deadlock.TransferMoney.Account;

/**
 * 多人同时转账，依然很危险
 */
public class MultiTransferMoney {
    //总共账户
    private static final int NUM_ACCOUNTS = 500;
    //每一个人的钱
    private static final int NUM_MONEY = 1000;
    //转账次数
    private static final int NUM_NUM = 1000000;
    //线程个数
    private static final int NUM_THREADS = 20;
    public static void main(String[] args) {
        Random rnd = new Random();
        Account[] accounts = new Account[NUM_ACCOUNTS];
        for (int i = 0; i < accounts.length; i++) {
            accounts[i] = new Account(NUM_MONEY);
        }

        class TransferMoneyThread extends Thread{
            @Override
            public void run() {
                for (int i = 0; i < NUM_NUM; i++) {
                    int from = rnd.nextInt(NUM_ACCOUNTS);
                    int to = rnd.nextInt(NUM_ACCOUNTS);
                    int money = rnd.nextInt(NUM_MONEY);
                    System.out.println("账户：" + accounts[from] + "剩余" + accounts[from].balance + "元向账户：" + accounts[to] + "中转" + money + "元");
                    TransferMoney.transferMoney(accounts[from], accounts[to], money);
                }
            }
        }
        for (int i = 0; i < NUM_THREADS; i++) {
            TransferMoneyThread thread = new TransferMoneyThread();
            thread.start();
        }
    }
}
