package skills;

public class IsAliveTest {

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        Thread t = new Thread(myThread);
        System.out.println("main start t is alive: " + t.isAlive() + ", " + t.getId());
        t.setName("AAAA");
        t.start();
        System.out.println("main end t is alive: " + t.isAlive() + ", " + t.getId());
    }

    static class MyThread extends Thread {
        public MyThread() {
            System.out.println("MyThread");
            System.out.println("current thread: " + currentThread().getName() + ", " + currentThread().getId() + ", "
                    + currentThread().isAlive());

            System.out.println("this: " + this.getName() + ", " + this.getId() + ", " + this.isAlive());
        }

        @Override
        public void run() {
            System.out.println("run");
            System.out.println("current thread: " + currentThread().getName() + ", " + currentThread().getId() + ", "
                    + currentThread().isAlive());

            System.out.println("this: " + this.getName() + ", " + this.getId() + ", " + this.isAlive());
        }
    }
}
