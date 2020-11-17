package threadcoreknowledge.stopthreads;

/**
 * 错误的停止方法：用stop()来停止线程，会导致线程运行一半突然停止，没办法完成一个基本单位的操作(一个连队)，会造成脏数据(多领取和少领取)
 */
public class StopThread implements Runnable{
    @Override
    public void run() {
        //模拟指挥军队：一共有五个连队，每个连队10人，以连队为单位，发放东西
        for(int i=0;i<5;i++){
            System.out.println("第"+i+"连队，开始领取");
            for (int j=0;j<10;j++){
                System.out.println("j:"+j);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("第"+i+"连队，已经领完");
        }
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new StopThread());
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //会导致 流程破坏 不知道数据的正确性
        thread.stop();
    }
}
