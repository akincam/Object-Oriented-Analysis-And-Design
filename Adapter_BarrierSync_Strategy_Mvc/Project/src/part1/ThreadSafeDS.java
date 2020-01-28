package part1;

/**
 * This class implements IThreadSafeDS interface
 * @author Akin Cam
 */
public class ThreadSafeDS implements IThreadSafeDS {
    /**
     * synchronized keyword used to provide thread safe
     * @param o object can be added
     */
    @Override
    public synchronized void safeInsert(Object o) {
        System.out.println("Thread Safe Insertion");
    }

    /**
     * synchronized keyword used to provide thread safe
     * @param o object can be deleted
     */
    @Override
    public synchronized void safeRemove(Object o) {
        System.out.println("Thread Safe Remove");
    }

    /**
     *
     * synchronized keyword used to provide thread safe
     * @param index DS value index
     * @return Object random access
     */
    @Override
    public synchronized Object safeGet(int index) {
        System.out.println("Thread Safe Get");
        return null;
    }
}
