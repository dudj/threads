package background;

/**
 * 初始化未完毕，就this赋值
 * 时间不同，导致结果不同
 */
public class MultiThreadError4 {
    static Point point;

    public static void main(String[] args) throws InterruptedException {
        new PointMaker().start();
//        Thread.sleep(10);
        Thread.sleep(105);
        if(point != null){
            System.out.println(point);
        }
    }
}

class Point{
    private final int x,y;

    public Point(int x, int y) throws InterruptedException {
        this.x = x;
        MultiThreadError4.point = this;
        Thread.sleep(100);
        this.y = y;
    }

    @Override
    public String toString() {
        return "Point{" +
            "x=" + x +
            ", y=" + y +
        '}';
    }
}

class PointMaker extends Thread{
    @Override
    public void run() {
        try {
            Point point = new Point(1, 1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}