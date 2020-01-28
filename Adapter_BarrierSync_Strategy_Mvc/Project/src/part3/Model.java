package part3;

import part2.DFTSimulator;

/**
 * Model of gui
 * @author Akin Cam
 */
public class Model {
    /**
     * Simulation class variable
     */
    DFTSimulator dftSimulator;
    /**
     * gui threads , to provide remain responsive during the
     * calculations
     */
    Thread one;

    /**
     * Creates dftSimulator and runs in a new thread
     * @param length  matrix lenhgth
     * @param threadSize  thread size
     * @param method oldversion or new version
     * @throws InterruptedException when error occurs state wait
     */
    public void performDFT(int length,int threadSize,int method) throws InterruptedException {
        this.one = new Thread() {
            public void run() {
                try {
                    dftSimulator = new DFTSimulator();
                    dftSimulator.DFTSimulator(length,threadSize,method);
                } catch(InterruptedException v) {
                    System.out.println(v);
                }
            }
        };
        one.start();
    }

}
