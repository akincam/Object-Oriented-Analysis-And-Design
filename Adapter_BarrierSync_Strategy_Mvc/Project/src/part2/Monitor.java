package part2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author Akin Cam
 * Mutex And Monitor.
 */
public class Monitor implements BarrierBehaviour {
    /**
     * Mutex variable
     */
    private Lock lock;
    /**
     * Condition variable
     */
    private Condition condition;
    /**
     * Number of threads to determine waited threadNum
     */
    private int threadNum;
    /**
     * Waited threads num
     */
    private int lockingThreadCount;

    /**
     *Constructor
     * @param threadNum Number of threads to determine waited threadNum
     * @param lock  Mutex variable
     * @param condition Condition variable
     */
    public Monitor(int threadNum,Lock lock,Condition condition){
        this.threadNum = threadNum;
        this.lockingThreadCount = 0;
        this.lock = lock;
        this.condition = condition;
    }

    /**
     *first mutex lock. increases lockingThread size
     * while unlock condition.await() makes a barrier
     * last thread arrive there send signal All
     */
    public void waitThread(){
        lock.lock();
        try{
            lockingThreadCount++;
            while(this.lockingThreadCount<(this.threadNum)){
                condition.await();
            }
            if(lockingThreadCount == this.threadNum)
                condition.signalAll();

        }
        catch (InterruptedException e){

        }finally {
            lock.unlock();
        }
    }
}
