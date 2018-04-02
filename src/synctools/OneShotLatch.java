package synctools;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class OneShotLatch {
    private class Sync extends AbstractQueuedSynchronizer {
        @Override
        protected int tryAcquireShared(int arg) {
            return (getState() == 1) ? 1 : -1;
        }

        @Override
        protected boolean tryReleaseShared(int arg) {
            setState(0);
            return true;
        }
    }

    private final Sync sync = new Sync();

    public void await() throws InterruptedException {
        sync.acquireInterruptibly(0);
    }

    public void signal() {
        sync.releaseShared(0);
    }
}
