package part2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Akin Cam
 * Simulation of DFS.
 * creates matrix
 * creates sycnorized variables
 * creates threas and call dftMethods.
 */
public class DFTSimulator {
    /**
     * Input matrix
     */
    private         Matrix      m1;
    /**
     * Input matrix
     */
    private         Matrix      m2;
    /**
     *return matrix
     */
    public          Matrix      m3;
    /**
     * to Barrier problem with wait and notifyall
     */
    private BarrierOldVersion barrierOldVersion;
    /**
     * mutex for monitor class
     */
    private final   Lock        lock       = new ReentrantLock();
    /**
     * condition variable for monitor class
     */
    private         Condition   condition  = lock.newCondition();
    /**
     *Threads array
     */
    public          Thread []   threads;
    /**
     *finish time of method.
     */
    public          long        duration;
    /**
     * Barrier Behaviour
     */
    public BarrierBehaviour barrierBehaviour;
    /**
     * This method initalize variables, creates threads,start threads and calculate times
     * @param length matrix length
     * @param threadSize thread length
     * @param method old version or new version
     * @throws InterruptedException when error occurs state wait
     */
    public void DFTSimulator(int length,int threadSize,int method) throws InterruptedException {
        m1              = new Matrix(length);
        m2              = new Matrix(length);
        m3              = new Matrix(length);
        threads         = new Thread[threadSize];
        barrierOldVersion = new BarrierOldVersion(threadSize);
        condition       = lock.newCondition();
        if(method == 2){
            barrierBehaviour = new Monitor(threadSize,lock,condition);
        }
        else{
            barrierBehaviour = new BarrierOldVersion(threadSize);
        }
        m1.fillMatrix();
        m2.fillMatrix();
        for(int i=0;i<threadSize;i++){
            threads[i] = new Thread(new DFTSolver(length,threadSize,m1,m2,m3,i+1,barrierBehaviour,method),"Thread"+i);
        }
        long startTime = System.nanoTime();
        for(int i=0;i<threadSize;i++){
            threads[i].start();
        }
        for(int i=0;i<threadSize;i++){
            threads[i].join();
        }
        System.out.println("İşlem Tamlandı");
        long endTime = System.nanoTime();
        duration = (endTime - startTime);
        System.out.println("Geçen süre :"+duration/1000000+" ms");
        System.out.println(m3.toString());
    }
}
