package singleton;

/**
 * 饿汉式(静态常量) 可用
 * static 在类加载的时候已经同步
 */
public class Singleton1 {
    private final static Singleton1 INSTANCE = new Singleton1();

    public static Singleton1 getINSTANCE() {
        return INSTANCE;
    }
    public Singleton1() {
    }
}
