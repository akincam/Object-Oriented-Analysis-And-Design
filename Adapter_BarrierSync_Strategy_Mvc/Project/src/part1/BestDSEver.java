package part1;

/**
 * brilliant and obscure data structure: BestDSEver interface
 * linear complexity insertion/deletion/random access
 * @author Akin Cam
 */
public interface BestDSEver {

    /**
     *insert
     * @param o object can be added
     */
    public void insert(Object o);

    /**
     *remove
     * @param o object can be deleted
     */
    public void remove(Object o);

    /**
     *random access
     * @param index index
     * @return Object random access
     */
    public Object get(int index);
}
