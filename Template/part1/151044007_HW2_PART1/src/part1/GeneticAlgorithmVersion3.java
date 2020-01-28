package part1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * This method implements crossover and selection
 * @author Akin Cam
 */
public class GeneticAlgorithmVersion3 extends GeneticAlgorithm{
    /**
     * Constructor -- uses super
     * @param populationSize population size
     */
    public GeneticAlgorithmVersion3(int populationSize) {
        super(populationSize);
    }

    /**
     * Takes a random value
     * Make a tournamet and select the best probability value
     * And takes new random value do 2 step from above until population size has completed.
     * @param individuals Population members
     * @return new selection list
     */
    public List<Individual> selection(ArrayList<Individual> individuals) {
        int selection = 0;
        List<Integer> index = new ArrayList();
        List<Individual> selectionList = new ArrayList<>();
        while(populationSize>selection){
            for(int i=0;i<3;i++){
                index.add(ThreadLocalRandom.current().nextInt(0, populationSize));
            }
            double temp=-1;
            int indexed = 0;
            for(int i : index){
                if(individuals.get(i).getFitness()>temp){
                    indexed = i;
                    temp = individuals.get(i).getFitness();
                }
            }
            index.clear();
            selectionList.add(individuals.get(indexed));
            selection++;
        }
        return selectionList;
    }

    /**
     * Uses crossover helper
     * Because Algorithm version 1 implements same method -- one point crossover
     */
    public void crossover() {
       crossoverHelper();
    }
}
