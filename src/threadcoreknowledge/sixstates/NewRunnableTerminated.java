package threadcoreknowledge.sixstates;

/**
 * 三种状态 创建、可运行、终止
 * 即使是正在运行，也是Runnable状态，而不是Running
 */
public class NewRunnableTerminated implements Runnable{
    public static void main(String[] args) {
        Thread thread = new Thread(new NewRunnableTerminated());
        //NEW状态
        System.out.println(thread.getState());
        thread.start();
        //Runnable状态
        System.out.println(thread.getState());
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Runnable状态，即使是正在运行，也是Runnable状态，而不是Running
        System.out.println(thread.getState());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Terminated
        System.out.println(thread.getState());
    }

    @Override
    public void run() {
        for (int i = 0; i <10000 ; i++) {
            System.out.println(i);
        }
    }
}
