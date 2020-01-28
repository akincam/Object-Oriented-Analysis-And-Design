package part1;

/**
 * Adapter Class for BestDSEver using by ThreadSafeDS
 * @author Akin Cam
 */
public class ThreadSafeDSAdapter implements BestDSEver {
    /**
     *Keeps threadSafeDS
     */
    private ThreadSafeDS threadSafeDS;

    /**
     * Constructor
     * @param threadSafeDS to adaptee thread safe
     */
    public ThreadSafeDSAdapter(ThreadSafeDS threadSafeDS){
        this.threadSafeDS = threadSafeDS;
    }

    /**
     * calls thread safe insert
     * @param o object can be added
     */
    @Override
    public void insert(Object o) {
        threadSafeDS.safeInsert(o);
    }

    /**
     * calls thread safe remove
     * @param o object can be deleted
     */
    @Override
    public void remove(Object o) {
        threadSafeDS.safeRemove(o);
    }

    /**
     * calls thread safe get random access
     * @param index value index
     * @return Objects
     */
    @Override
    public Object get(int index) {
        return threadSafeDS.safeGet(index);
    }
}
