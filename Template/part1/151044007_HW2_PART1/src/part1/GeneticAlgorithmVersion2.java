package part1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * This method implements crossover and selection
 * @author Akin Cam
 */
public class GeneticAlgorithmVersion2 extends GeneticAlgorithm {
    /**
     * Constructor -- uses super
     * @param populationSize population size
     */
    public GeneticAlgorithmVersion2(int populationSize) {
        super(populationSize);
    }

    /**
     * This method sorts the fitness
     * And set the members's fitness value start from 1
     * Set the total fitness and makes roulette wheel selection method
     * @param individuals Population members
     * @return new selection list
     */
    public List<Individual> selection(ArrayList<Individual> individuals) {
        int fitnessCount               = 1;
        totalFitness                   = 0;
        int    sum                     = 0;
        double rand                    = 0;
        int    spin                    = 0;
        ArrayList<Individual> selectionList = new ArrayList<>();
        sort(individuals,individuals.size());
        for(Individual individual : individuals)
            individual.setFitness(fitnessCount++);
        calculateTotalFitness(individuals);
        while(populationSize>spin) {
            rand = ThreadLocalRandom.current().nextDouble(0, totalFitness);
            sum = 0;
            int i =0;
            while(true) {
                sum += individuals.get(i).getFitness();
                if (sum >= rand && individuals.get(i).getFitness()>0) {
                    spin++;
                    selectionList.add(individuals.get(i));
                    break;
                }
                i++;
                if(i == populationSize) {
                    break;
                }
            }
        }
        return selectionList;
    }

    /**
     * Sorts the members of population their fitness values
     * @param list Population members
     * @param number population size
     */
    public void sort(ArrayList<Individual> list, int number) {
        for(int i=0;i<number-1;i++){
            for(int j=1;j<number;j++){
                if(list.get(j-1).getFitness()>list.get(j).getFitness()){
                    Individual temp = list.get(j-1);
                    list.set(j-1,list.get(j));
                    list.set(j,temp);
                }
            }
        }
    }

    /**
     *Crossover method super class has abstract type
     * Two point crossover method used
     * Takes two random value
     * Makes members pair 2 by 2
     * a member's 0 to first value of genes not change
     * first value to second value changed -- taken from pair population member
     * second value to genes.size() not changed
     */
    public void crossover() {
        int index1,index2;
        String current1,current2,temp1,temp2;
        for(int i=0;i<populationSize;i+=2){
            index1   = ThreadLocalRandom.current().nextInt(0, 124);
            index2   = ThreadLocalRandom.current().nextInt(index1, 124);
            temp1    = individuals.get(i).getGene().substring(index1,index2);
            temp2    = individuals.get(i+1).getGene().substring(index1,index2);
            current1 = new StringBuilder().append(individuals.get(i).getGene().substring(0,index1)).append(temp2).append(individuals.get(i).getGene().substring(index2)).toString();
            current2 = new StringBuilder().append(individuals.get(i+1).getGene().substring(0,index1)).append(temp1).append(individuals.get(i+1).getGene().substring(index2)).toString();
            individuals.get(i).setGene(current1);
            individuals.get(i+1).setGene(current2);
        }
    }
}
