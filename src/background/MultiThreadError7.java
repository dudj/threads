package background;

/**
 * 用工厂模式修复刚刚注册器中初始化问题
 */
public class MultiThreadError7 {
    int count;
    private EventListener listener;
    public MultiThreadError7(MySource source) {
        listener = new EventListener() {
            @Override
            public void onEvent(Event e) {
                System.out.println("\n 我的搭配的数字是：" + count);
            }
        };
        for (int i = 0; i < 10000; i++) {
            System.out.print(i);
        }
        count = 100;
    }

    public static MultiThreadError7 getInstance(MySource source){
        MultiThreadError7 safeListener = new MultiThreadError7(source);
        source.registerListener(safeListener.listener);
        return safeListener;
    }
    public static void main(String[] args) {
        MultiThreadError5.MySource mySource = new MultiThreadError5.MySource();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mySource.eventCome(new MultiThreadError5.Event(){

                });
            }
        }).start();
        MultiThreadError5 multiThreadError5 = new MultiThreadError5(mySource);
    }
    static class MySource{
        private EventListener listener;
        void registerListener(EventListener eventListener){
            this.listener = eventListener;
        }
        void eventCome(Event e){
            if(listener != null ){
                listener.onEvent(e);
            }else{
                System.out.println("还未初始化完毕");
            }
        }
    }
    interface EventListener{
        void onEvent(Event e);
    }
    interface Event{
    }
}
