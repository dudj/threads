package threadcoreknowledge.createthreads;

public class ThreadStyle extends Thread{
    @Override
    public void run() {
        System.out.println("用Thread实现线程");
    }

    public static void main(String[] args) {
        new ThreadStyle().run();
    }
}
