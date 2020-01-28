package part1;

import java.util.concurrent.ThreadLocalRandom;

/**
 * This class keeps a member of population
 * @author AKIN CAM
 */
public class Individual {
    /**
     * Keeps the x1 and x2 as a binary(126 bits)
     */
    private  String       gene;
    /**
     * Keeps the each population member's fitness value
     */
    private  double       fitness;

    /**
     * Initialize values
     * Initialize population x1 and x2 and convert the value binary as a x1+x2
     */
    public Individual(){
        double        valueX;
        double        valueY;
        String        genesX;
        String        genesY;
        StringBuilder concatGenes = new StringBuilder();
        /*
         *if the values not valid try again
         */
        do {
            valueX      = generateDouble();
            valueY      = generateDouble();
        }while(valueX+valueY>5 || valueX+valueY<0);
        genesX  = decimalToBinary(valueX);
        genesY  = decimalToBinary(valueY);
        int i =0;
        /**
         * binary string setted a 126 bits
         * if smaller than 126 set first side '0' because this state not change the value of population.
         */
        while ((genesX.length()+i)<63){
            concatGenes.append('0');
            i++;
        }
        concatGenes.append(genesX);
        i = 0;

        while ((genesY.length()+i)<63){
            concatGenes.append('0');
            i++;
        }
        concatGenes.append(genesY);
        gene = concatGenes.toString();
    }

    /**
     * generate random double value 0 to 5
     * @return double number
     */
    private double generateDouble(){
        return ThreadLocalRandom.current().nextDouble(0, 5);
    }

    /**
     * converts double to binary
     * @param value population decimal number
     * @return String binary
     */
    private String decimalToBinary(double value){
        return Long.toBinaryString(Double.doubleToRawLongBits(value));
    }

    /**
     * getFitness
     * @return fitness value
     */
    public double getFitness() {
        return fitness;
    }
    /**
     * setFitness
     * sets fitness value
     * @param fitness member fitness value
     */
    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    /**
     * getGenes
     * @return genes of population
     */
    public String getGene() {
        return gene;
    }

    /**
     * setGenes
     * @param gene String genes 126 bit sets the this value.
     */
    public void setGene(String gene) {
        this.gene = gene;
    }
}
