package part2;

/**
 * Main class
 */
public class Main {

    public static void main(String [] args) throws Exception {
        System.out.println("Length 32, Thread 1 -- Old Version");
        DFTSimulator dftSimulator = new DFTSimulator();
        dftSimulator.DFTSimulator(32,1,1);

        System.out.println("Length 32, Thread 4 -- Old Version");
        DFTSimulator dftSimulator2 = new DFTSimulator();
        dftSimulator2.DFTSimulator(32,4,1);

        System.out.println("Length 64, Thread 1 -- New Version");
        DFTSimulator dftSimulator3 = new DFTSimulator();
        dftSimulator3.DFTSimulator(64,1,2);

        System.out.println("Length 64, Thread 8 -- New Version");
        DFTSimulator dftSimulator4 = new DFTSimulator();
        dftSimulator4.DFTSimulator(64,8,2);
        return;
    }
}

