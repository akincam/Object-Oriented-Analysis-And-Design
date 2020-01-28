package question1;

/**
 * SolverMethods interface yapisinin kullandigi metodlara uygun implement edilmis abstract Solver sınıfı.
 * @author Akin Cam
 */
public abstract class Solver {
    /**
     * Çözüm methodu secılmesı ıcın SolverMethods kullanılır.
     */
    private SolverMethods solverMethods;
    /**
     * Ax=b seklinde verilen matrısın Ax kısmını ıcerır
     */
    private double [][] matrix;
    /**
     * Ax=b seklinde verilen matrısın b kısmını ıcerır
     */
    private double [] vector;

    /**
     * Hangi methodun kullanıcağı belirlenir
     * @param sm hangi metodun dinamık olarak secılecegı belirlereyn degısken
     */
    public Solver(SolverMethods sm) {
        this.solverMethods = sm;
    }

    /**
     * hangi metodun dinamık olarak secılecegı belırlenır.
     * @param sm hangi metodun dinamık olarak secılecegı belirlereyn degısken
     */
    public void setSolverMethods(SolverMethods sm){
        this.solverMethods = sm;
    }

    /**
     * Verilen array matrıs ve vector olarak(Ax+By = C) ıkıye ayrılır.
     * SolverMethods interface sınıfında bulunan setSolver methodu cagrılır.
     * @param array Ax = b seklındekı denklemın sol tarafı
     */
    public void performSolver(double [][] array){
        matrix = new double[array.length][array.length];
        vector = new double[array.length];
        for(int i=0;i<array.length;i++){
            for(int j=0;j<array.length;j++){
                matrix[i][j] = array[i][j];
            }
            vector[i] = array[i][array[0].length-1];
        }
        solverMethods.solver(matrix,vector);
    }
}
