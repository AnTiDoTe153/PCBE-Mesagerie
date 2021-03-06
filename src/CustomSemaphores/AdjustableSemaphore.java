package CustomSemaphores;

import java.util.concurrent.Semaphore;

final public class AdjustableSemaphore {

    private final ResizeableSemaphore semaphore;
    private int maxPermits;

    public AdjustableSemaphore(int maxPermits) {
        semaphore = new ResizeableSemaphore();
        this.maxPermits = maxPermits;
    }

    public synchronized void setMaxPermits(int newMax) {
        if (newMax < 1) {
            throw new IllegalArgumentException("Semaphore size must be at least 1");
        }

        int delta = newMax - this.maxPermits;
        if (delta == 0) {
            return;
        } else if (delta > 0) {
            this.semaphore.release(delta);
        } else {
            this.semaphore.reducePermits(-delta);
        }
        this.maxPermits = newMax;
    }

    public void drainPermits(){
        this.semaphore.drainPermits();
    }

    public void release() {
        this.semaphore.release();
    }

    public void acquire() throws InterruptedException {
        this.semaphore.acquire();
    }


    private static final class ResizeableSemaphore extends Semaphore {

        ResizeableSemaphore() {
            super(0, true);
        }

        @Override
        protected void reducePermits(int reduction) {
            super.reducePermits(reduction);
        }
    }
}
