package part2;

/**
 * @author Akin Cam
 * Syncronized Barrier implemented.
 */
public class BarrierOldVersion implements BarrierBehaviour {
    /**
     * Number of threads to determine waited threadNum
     */
    private int threadNum;
    /**
     * Waited threads num
     */
    private int lockingThreadCount;

    /**
     * Constructor
     * @param threadNum total threads num
     */
    public BarrierOldVersion(int threadNum) {
        this.threadNum          = threadNum;
        this.lockingThreadCount = 0;
    }

    /**
     * gets lockingThreadCount
     * @return gets number of waited threads
     */
    public int getLockingThreadCount() {
        return lockingThreadCount;
    }

    /**
     * This methods first increase number of waited thread.
     * Then wait the thread.
     * Last thread arrive there all threads unlock(notifyAll)
     * @throws InterruptedException when error occurs state wait
     */
    public synchronized void waitThread() throws InterruptedException {
        this.lockingThreadCount++;
        if(this.lockingThreadCount<(this.threadNum)){
            wait();
        }
        else if(lockingThreadCount == this.threadNum){
            notifyAllThread();
        }
        else{
            throw new InterruptedException("Out of Bound Thread Size");
        }
    }

    /**
     * When a thread want to be unlock.
     * @throws InterruptedException when error occurs state wait
     */
    public synchronized void notifyThread() throws InterruptedException {
        if(this.lockingThreadCount<this.threadNum){
            notify();
            this.lockingThreadCount--;
        }
        else{
            throw new InterruptedException("There is no pending thread");
        }

    }

    /**
     * Last thread arrive there all threads notify
     * @throws InterruptedException when error occurs state wait
     */
    public synchronized void notifyAllThread() throws InterruptedException {
            notifyAll();
            this.lockingThreadCount=0;
    }
}
