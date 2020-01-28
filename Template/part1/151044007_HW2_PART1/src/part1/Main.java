package part1;

public class Main {
    public  static void main(String[] args) throws InterruptedException {
        System.out.println("Version 1----->\n");
        GeneticAlgorithm gn1 = new GeneticAlgorithmVersion1(30);
        gn1.geneticAlgorithmSolver();
        System.out.println("--------------\n MAX FİTTEST: "+gn1.fittest+"\n--------------\n");
        System.out.println("\nVersion 2----->\n");
        GeneticAlgorithm gn2 = new GeneticAlgorithmVersion2(30);
        gn2.geneticAlgorithmSolver();
        System.out.println("--------------\n MAX FİTTEST: "+gn2.fittest+"\n--------------\n");
        System.out.println("\nVersion 3----->\n");
        GeneticAlgorithm gn3 = new GeneticAlgorithmVersion3(30);
        gn3.geneticAlgorithmSolver();
        System.out.println("--------------\n MAX FİTTEST: "+gn3.fittest+"\n--------------\n");

    }
}
