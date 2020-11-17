package threadcoreknowledge.stopthreads;

/**
 * 最佳实践2：在catch子语句中调用Thread.currentThread().interrupt()
 * 来恢复设置中断状态，以便于在后续的执行中，依然能够检查到刚才发生了中断
 * 回到RightWayStopThreadInProd补上中断，让它跳出
 */
public class RightWayStopThreadInProd2 implements Runnable{
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new RightWayStopThreadInProd2());
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }

    @Override
    public void run() {
        //run方法抛出exception
        while (true){
            if(Thread.currentThread().isInterrupted()){
                System.out.println("Interrupted，程序运行结束");
                break;
            }
            reInterrupt();
        }
    }
    private void reInterrupt(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }
}
