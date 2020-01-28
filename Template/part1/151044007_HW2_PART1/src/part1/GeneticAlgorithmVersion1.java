package part1;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * This method implements crossover and selection
 * @author Akin Cam
 */
public class GeneticAlgorithmVersion1 extends GeneticAlgorithm {

    /**
     * Constructor -- uses super
     * @param populationSize population size
     */
    public GeneticAlgorithmVersion1(int populationSize) {
        super(populationSize);
    }

    /**
     * Calculate probability.
     * Get a random value 0 to total value
     * Spin the members
     * when during spin a member matching random value, take this value and add new population list
     * if fitness value is bigger it has higher selection rate
     * @param individuals Population members
     * @return new selection list
     */
    public ArrayList<Individual> selection(ArrayList<Individual> individuals){
        double    sum                  = 0;
        double    rand;
        int       spin                 = 0;
        ArrayList<Individual> lst = new ArrayList<>();
        while(populationSize>spin) {
            if(Double.isNaN(totalFitness))
                rand = ThreadLocalRandom.current().nextDouble(0, fittest);
            else
                rand = ThreadLocalRandom.current().nextDouble(0, totalFitness+1);
            sum = 0;
            int i =0;
            while(true) {
                if(Double.isNaN(sum)){
                    spin++;
                    lst.add(individuals.get(i));
                    break;
                }
                sum += individuals.get(i).getFitness();
                if (sum >= rand && individuals.get(i).getFitness()>0) {
                    spin++;
                    lst.add(individuals.get(i));
                    break;
                }
                i++;
                if(i == populationSize) {
                    i = 0;
                    for(Individual in : individuals)
                        in = new Individual();
                    return lst = individuals;
                }
            }
        }
        super.individuals =(ArrayList<Individual>) lst.clone();
        return lst;
    }

    /**
     * Uses crossover helper
     * Because Algorithm version 3 implements one point crossover
     */
    public void crossover() {
        crossoverHelper();
    }
}
