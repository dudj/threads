package background;

import java.util.HashMap;
import java.util.Map;

/**
 * 发布溢出
 * 使用"副本"代替"真身"
 */
public class MultiThreadError3 {
    private Map<String,String> states;

    public MultiThreadError3() {
        states = new HashMap<>();
        states.put("1","周一");
        states.put("2","周二");
        states.put("3","周三");
        states.put("4","周四");
    }
    //溢出 导致数据出问题
    public Map<String, String> getStates() {
        return states;
    }
    public Map<String,String> getStatesImproved(){
        return new HashMap<>(states);
    }

    public static void main(String[] args) {
        MultiThreadError3 multiThreadError3 = new MultiThreadError3();
//        Map<String, String> states = multiThreadError3.getStates();
//        System.out.println(states.get("1"));
//        states.remove("1");
//        System.out.println(states.get("1"));
        System.out.println(multiThreadError3.getStatesImproved().get("1"));
        multiThreadError3.getStatesImproved().remove("1");
        System.out.println(multiThreadError3.getStatesImproved().get("1"));

    }
}
