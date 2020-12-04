package deadlock;

/**
 * 演示活锁问题
 * 逻辑：
 * 以夫妻吃饭为例，只有一个吃饭的工具。比如：筷子、勺子；在吃饭的时候两者总是谦让 谁饿了谁先吃
 */
public class LiveLock {
    static  class Spoon{
        private Diner owner;

        public Spoon(Diner owner) {
            this.owner = owner;
        }
        public Diner getOwner() {
            return owner;
        }
        public void setOwner(Diner owner) {
            this.owner = owner;
        }
        //使用者
        public synchronized void use(){
            System.out.println(owner.name + "正在使用");
        }
    }
    static class Diner{
        private String name;
        private Boolean isHungry;

        public Diner(String name) {
            this.name = name;
            isHungry = true;
        }
        //另一半
        public void canEatWith(Spoon spoon,Diner diner){
            while(isHungry){
                //当前拥有者不是自己 等待
                if(spoon.owner != this){
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    continue;
                }
                //自己如果不是饥饿的 那就让出
                if(diner.isHungry){
                    System.out.println(this.name + ":" + "哈哈哈，" + diner.name + "你先吃吧");
                    spoon.setOwner(diner);
                    continue;
                }
                //自己先吃
                spoon.use();
                isHungry = false;
                System.out.println(this.name + ":我吃完了");
                spoon.setOwner(diner);
            }
        }
    }

    public static void main(String[] args) {
        Diner husband = new Diner("董永");
        Diner wife = new Diner("七仙女");

        Spoon spoon = new Spoon(husband);
        new Thread(new Runnable() {
            @Override
            public void run() {
                husband.canEatWith(spoon,wife);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                wife.canEatWith(spoon,husband);
            }
        }).start();
    }
}
