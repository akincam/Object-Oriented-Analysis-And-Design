package part1;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Abstract GeneticAlgorithm class
 * Keeps the main algoritm method
 * Keeps abstract method to chamge algorithm implement type.
 * @author Akin Cam
 */
public abstract  class GeneticAlgorithm {
    /**
     *Keeps the population members
     */
    public ArrayList<Individual>    individuals;

    /**
     *Keeps the population size
     */
    public int                      populationSize;

    /**
     *Keeps the total fitness
     */
    public double                   totalFitness;

    /**
     *Keeps the max fittest value
     */
    public double                   fittest     = 0;

    /**
     *Keeps the iteration count of algorithm
     */
    public int                      iteration   = 0;

    /**
     * Constructor sets the populationsize
     * @param populationSize sets the population size
     */
    public GeneticAlgorithm(int populationSize){
        totalFitness        = 0;
        if(populationSize<=0)
            populationSize = 10;
        this.populationSize = populationSize;

    }

    /**
     * Main method of algorithm loop
     * @throws InterruptedException Thread.sleep
     * final method not to change or override(for template method)
     * initalize population with given population size
     * calculate fitness each population member
     * To show clearly Thread.sleep added
     * Sequentially:
     * Loop{
     *  Selection -- abstract method to change other versions
     *  Crossover -- abstract method to change other versions
     *  Mutation
     *  ComputeFitness
     *  calculateMaxFit - to find optimal solution
     *}
     */
    public final void  geneticAlgorithmSolver() throws InterruptedException {
        iteration       = 0;
        fittest         = 0;
        totalFitness    = 0;
        if(individuals!=null && individuals.size()>0)
            individuals.clear();
        initializePopulation();
        computeFitness();
        while (iteration<500) {
            individuals = (ArrayList<Individual>) selection(individuals);
            crossover();
            if (new Random().nextInt() % 7 < 5)
                individuals = mutation();
            computeFitness();
            iteration++;
            calculateMaxFit();
        }
    }

    /**
     * calculate maxFittestValue
     */
    public void calculateMaxFit(){
        boolean boo = false;
        for(Individual in : individuals) {
            if (in.getFitness() > fittest) {
                fittest = in.getFitness();
                boo = true;
            }
        }
        if(boo)
            System.out.println("Fittest value: "+fittest);
    }
    /**
     *Initialize  the population
     * add genes random double numbers.
     */
    public void initializePopulation() {
        individuals = new ArrayList();
        for (int i = 0; i < populationSize; i++) {
            individuals.add(new Individual());
        }
    }

    /**
     * Fitness calculated and not appropriate members's fitness set 0
     */
    public void computeFitness(){
        double fitness = 0;
        double x1      = 0;
        double x2      = 0;
        for(Individual individual : individuals){
            x1            = this.getX1Value(individual);
            x2            = this.getX2Value(individual);
            fitness       = 20*x1*x2+16*x2-2*Math.pow(x1,2)-Math.pow(x2,2)-Math.pow(x1+x2,2);
            /*
             *if
             *  fitness<0
             *  x1 and x2 >5 or x1+x2 <0 or each one <0 or >0 sets fitness 0
             */
            if(fitness<0 || x1+x2>5.0 || x1+x2<0.0 || x1<0|| x2<0 || x1>5 || x2 >5) {
                individual.setFitness(0);
            }
            /*
             * set fitness value
             */
            else {
                individual.setFitness(fitness);
            }
        }
        /*
         * total fitness calculated
         */
        calculateTotalFitness(individuals);
    }

    /**
     * Calculate total fitness to find probability and selection
     * @param individuals population members
     */
    public void calculateTotalFitness(List<Individual> individuals){
        totalFitness = 0;
        for(Individual individual : individuals) {
            totalFitness+=individual.getFitness();
        }
    }

    /**
     * Abstract method. extend classes implement this method.
     * @param list population list
     * @return selection list
     */
    public abstract List<Individual> selection(ArrayList<Individual> list);

    /**
     *Abstract method. extend classes implement this method.
     */
    public abstract void crossover();

    /**
     * Sets gene and change its value
     * @return Mutated members.
     */
    public ArrayList<Individual> mutation() {
        ArrayList<Individual> lst = new ArrayList<>();
        char [] arr;
        for(Individual in : individuals) {
            int index     = ThreadLocalRandom.current().nextInt(1,125);
            arr = in.getGene().toCharArray();
            if(arr[index] == '0')
                arr[index] = '1';
            else{
                arr[index] = '0';
            }
            in.setGene(String.valueOf(arr));
            Individual individual = new Individual();
            individual.setGene(String.valueOf(arr));
            lst.add(individual);
        }
        return lst;
    }

    /**
     * convert binary to doble
     * @param genes binary String
     * @return double value
     */
    private double geneValue(String genes){
        return Double.longBitsToDouble(new BigInteger((genes), 2).longValue());
    }

    /**
     * get x1 genes
     * @param individual member of population
     * @return x1 genes
     */
    public double getX1Value(Individual individual){
        return geneValue(individual.getGene().substring(0,63));
    }

    /**
     * get x2 genes
     * @param individual  member of population
     * @return x2 genes
     */
    public double getX2Value(Individual individual){
        return geneValue(individual.getGene().substring(63));
    }

    /**
     * Crossover helper methdod to prevent dublicate method version of 1 and 3
     * Take a random value
     * Make pair every two member
     * First side of a member(0-value) not changed
     * Second side of member(value--) taken from its pair member.
     */
    public void crossoverHelper(){
        int index;
        String current1,current2;
        for(int i=0;i<populationSize;i+=2){
            index = ThreadLocalRandom.current().nextInt(0, 124);
            String temp1 = individuals.get(i).getGene().substring(index);
            String temp2 = individuals.get(i+1).getGene().substring(index);
            current1 = new StringBuilder().append(individuals.get(i).getGene().substring(0,index)).append(temp2).toString();
            current2 = new StringBuilder().append(individuals.get(i+1).getGene().substring(0,index)).append(temp1).toString();
            individuals.get(i).setGene(current1);
            individuals.get(i+1).setGene(current2);
        }
    }

}
