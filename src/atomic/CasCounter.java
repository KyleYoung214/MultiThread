package atomic;

public class CasCounter {
    private SimulatedCAS value;

    public int getValue() {
        return value.get();
    }

    public int increment() {
        int v;
        do {
            v = getValue();
        } while (v != value.compareAndSwap(v, v + 1));
        return v + 1;
    }

    public int increment2() {
        int v = getValue();
        while (v != value.compareAndSwap(v, v + 1)) {
            // wait for some time?
            // or roll back?
            v = getValue();
        }

        return v + 1;
    }
}
