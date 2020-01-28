package part1;

/**
 * ThreadSafeDS interface
 *  @author Akin Cam
 */
public interface IThreadSafeDS {

    /**
     *insert threadSafe
     * @param o object can be added
     */
    public void safeInsert(Object o);

    /**
     *remove threadSafe
     * @param o object can be deleted
     */
    public void safeRemove(Object o);

    /**
     *random access threadSafe
     * @param index index
     * @return Object random access
     */
    public Object safeGet(int index);
}
