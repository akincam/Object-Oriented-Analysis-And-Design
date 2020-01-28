package part1;

/**
 * Main method to test Thread Safe DS
 *  @author Akin Cam
 */
public class TestThreadSafeDS {

    public static void main(String [] args){
        ThreadSafeDS iThreadSafeDS = new ThreadSafeDS();
        System.out.println("----------------------------------------------------------------");
        System.out.println("ThreadSafeDS---->");
        iThreadSafeDS.safeInsert(new Object());
        iThreadSafeDS.safeRemove(new Object());
        iThreadSafeDS.safeGet(3);

        System.out.println("----------------------------------------------------------------");
        System.out.println("ThreadSafeDS Adapter---->");
        ThreadSafeDSAdapter threadSafeDSAdapter = new ThreadSafeDSAdapter(iThreadSafeDS);
        threadSafeDSAdapter.insert(new Object());
        threadSafeDSAdapter.remove(new Object());
        threadSafeDSAdapter.get(3);
        return;
    }
}
