package background;

import java.util.HashMap;
import java.util.Map;

/**
 * 构造函数中新建线程
 * 子线程如果没有运行完毕，运行的话，会出现空指针
 */
public class MultiThreadError6 {
    private Map<String,String> states;

    public MultiThreadError6() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                states = new HashMap<>();
                states.put("1","周一");
                states.put("2","周二");
                states.put("3","周三");
                states.put("4","周四");
            }
        }).start();
    }
    //溢出 导致数据出问题
    public Map<String, String> getStates() {
        return states;
    }

    public static void main(String[] args) throws InterruptedException {
        MultiThreadError6 multiThreadError6 = new MultiThreadError6();
        //时间不同 ，结果不同
        Thread.sleep(1000);
        Map<String, String> states = multiThreadError6.getStates();
        System.out.println(states.get("1"));
    }
}
