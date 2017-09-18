package synctools;

public class ExampleCode {
    Object lock = new Object();

    void stateDependentMethod() throws InterruptedException {
        synchronized (lock) {
            while (!conditionPredicate()) {
                lock.wait();
            }
        }
    }

    boolean conditionPredicate() {
        return false;
    }
}
