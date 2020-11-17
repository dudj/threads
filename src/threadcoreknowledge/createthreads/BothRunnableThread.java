package threadcoreknowledge.createthreads;

/**
 * 同时 重写了run方法 同时传入了target
 */
public class BothRunnableThread {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("我来自Runnable");
            }
        }){
            //已经覆盖掉接入runnable的run
            @Override
            public void run() {
                System.out.println("我来自Thread");
            }
        }.start();
    }
}
