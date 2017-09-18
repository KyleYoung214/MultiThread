package communication;

import java.util.ArrayList;
import java.util.List;

public class WaitNotifyTest {

    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();

        Add add = new Add(lock);
        Subtract subtract = new Subtract(lock);

        ThreadA threadA = new ThreadA(add);
        ThreadB threadB1 = new ThreadB(subtract);
        ThreadB threadB2 = new ThreadB(subtract);

        threadB1.start();
        threadB2.start();

        Thread.sleep(200);

        threadA.start();
    }

    static class Add {
        private Object object;

        public Add(Object o) {
            object = o;
        }

        public void add() {
            synchronized (object) {
                ValueObject.list.add("content");
                object.notifyAll();
            }
        }
    }

    static class Subtract {
        private Object object;

        public Subtract(Object o) {
            object = o;
        }

        public void subtract() {
            try {
                synchronized (object) {
                    // using if will get IndexOutOfBoundsException, change it to while
//                    if (ValueObject.list.size() == 0) {
                    while (ValueObject.list.size() == 0) {
                        System.out.println("size is 0, wait");

                        object.wait();

                        System.out.println("after wait...");
                    }

                    ValueObject.list.remove(0);
                    System.out.println("list size = " + ValueObject.list.size());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class ValueObject {
        public static List list = new ArrayList<>();
    }

    static class ThreadA extends Thread {
        private Add add;

        public ThreadA(Add add) {
            this.add = add;
        }

        @Override
        public void run() {
            add.add();
        }
    }

    static class ThreadB extends Thread {
        private Subtract subtract;

        public ThreadB(Subtract subtract) {
            this.subtract = subtract;
        }

        @Override
        public void run() {
            subtract.subtract();
        }
    }
}
