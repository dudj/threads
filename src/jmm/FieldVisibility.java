package jmm;

/**
 * 演示可见性带来的问题
 *
 * a = 3,b = 2
 * a = 1,b = 2
 * a = 3,b = 3
 *
 * b=3;a=1 由于第二个线程看到了b的修改值，没有看到a的
 */
public class FieldVisibility {
    int a = 1;
    int b = 2;

    public static void main(String[] args) {
        while (true){
            FieldVisibility test = new FieldVisibility();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    test.change();
                }
            }).start();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    test.print();
                }
            }).start();

        }
    }

    private void print() {
        System.out.println("b="+b+";a="+a);
    }

    private void change() {
        a = 3;
        b = a;
    }
}
