package threadcoreknowledge.stopthreads;

/**
 * 最佳实践：catch了InterruptedExcetion之后的优先选择，在方法签名中抛出异常
 * try catch在内 已经把异常给吞了，while这里检测不到异常 所以要把异常返回回去
 *
 */
public class RightWayStopThreadInProd implements Runnable{
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new RightWayStopThreadInProd());
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }

    @Override
    public void run() {
        //run方法抛出exception
        while (true && !Thread.currentThread().isInterrupted()){
            System.out.println("go");
            try {
                throwInMethod();
            } catch (InterruptedException e) {
                System.out.println("保存日志");
                e.printStackTrace();
            }
        }
    }
    /**
     * 在休眠过程中 去中断
     * 在签名中实现
     * @throws InterruptedException
     */
    private void throwInMethod() throws InterruptedException {
        Thread.sleep(1000);
        /*try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }
}
