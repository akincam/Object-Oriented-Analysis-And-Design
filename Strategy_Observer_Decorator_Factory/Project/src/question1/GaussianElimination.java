package question1;

/**
 * Verilen bir matris ve esitliğin çözümü için kullanılacak GaussElimination Sınıfı
 * SolverMethods interface inden implements edilir.
 * @author Akin Cam
 */
public class GaussianElimination extends DetermineSolutionType implements SolverMethods{
    /**
     * Denklem sonucu mevcut ise bu degıskende tutulur.
     */
    private double [] solution;

    /**
     *
     * @param array Ax = b seklinde olan matrisin sol tarafını üst üçgen matris haline getirir.
     *              Üst ücgen matriste matrise çizilen kösegenin altındaki rakamlar 0 olur.
     * @return üst ücgen matrisi döndürür.
     */
    private double [][] topTriangleMatrix(double[][] array){
        for(int i=1;i<array.length;i++) {
            for (int k = 0; k < i; k++) {
                double pivot = (array[i][k] / array[k][k]);//sol üstten başlayarak 0 yapma ilemi için kullanılır. yukarıdan asagı.
                for (int j = 0; j < array[0].length; j++) {
                    array[i][j] += -array[k][j] * pivot; //soldan sağa elemanları 0 yapmak için kullanılır.
                }
            }
        }
        return array;
    }

    /**
     *
     * @param matrix Ax = b seklinde verilen matriste sol tarafı temsil eder.
     * @param vector Ax = b seklinde verilen matriste sağ tarafı temsil eder.
     * @return Ax = b seklinde verilen matrisler augmented matris
     *                                                          Ax By Cz | b1
     *                                                          Dx Ey Fz | c1
     *                                                          ...
     * seklinde return edilir.
     */
    private double [][] augmentedMatrix(double[][] matrix, double[] vector){
        double [][] augmentedArray = new double[matrix.length][matrix.length+1];
        for (int i = 0; i < matrix.length; i++) {
            System.arraycopy(matrix[i], 0, augmentedArray[i], 0, matrix.length);
            augmentedArray[i][matrix.length] = vector[i];
        }
        return augmentedArray;
    }

    /**
     * (3*3 için örnek verilmiştir)
     * Augmented array ardından üst üçgen matris oluşturulan matriste eger cözüm mevcut ise ilk olarak z sonra y sonra x bulunur.
     * İlk z bulunur çünkü üst ücgen matris olduğu için aşağıda sadece z 0 değildir(cözüm mevcut ise)
     * @param topTriangleMatrix topTriangleMatrix metodunun return değeri
     * @param solution eğer çözüm mevcut ise cözüm matrisi
     * @return sonuc return edilir.
     */
    private double [] backSubstituon(double[][] topTriangleMatrix, double[] solution){

        for (int i = topTriangleMatrix.length - 1; i >= 0; i--) {
            double sum = 0.0;
            for (int j = i + 1; j < topTriangleMatrix.length; j++) {
                sum += topTriangleMatrix[i][j] * solution[j];
            }
            solution[i] = (topTriangleMatrix[i][topTriangleMatrix[0].length-1] - sum) / topTriangleMatrix[i][i];
        }
        return solution;
    }

    /**
     * Matris ilk olarak augmented matrise daha sonra üst ücgen matrise dönüştürülür.
     * daha Sonra backSubstution ile çozum mevcut ise bulunur. Bu sonuc static olarak bulunan printResult ile kullanıcıya iletilir.
     * @param matrix Ax = b seklinde verilen matriste sol tarafı temsil eder.
     * @param vector Ax = b seklinde verilen matriste sağ tarafı temsil eder.
     *
     */
    public  void   solver(double[][] matrix, double[] vector) {
        double [][] augmentedArray;
        int counter=0;
        augmentedArray    = augmentedMatrix(matrix,vector);
        for(int i=0;i<augmentedArray.length;i++){
            if(augmentedArray[augmentedArray.length-1][i]==0){
                counter++;
            }
        }
        if(counter == augmentedArray.length) {
            Gui.printResult("Solution is-->\n" + SolutionTypes.NoSolution);
            return;
        }
        augmentedArray    = topTriangleMatrix(augmentedArray);
        this.solution = new double[augmentedArray.length];
        solution          = backSubstituon(augmentedArray,solution);

        SolutionTypes st  = setSolutionIsValid(solution);
        for(int i=0;i<solution.length;i++)
            System.out.println(solution[i]);
        if(st.toString().equals(SolutionTypes.Single.toString()))
            Gui.printResult("Solution is-->\n"+this.toString());
        else
            Gui.printResult("Solution is-->\n"+st.toString());
    }

    /**
     * Kullanıcıya sonucu göstermek için override edilmistir.
     * @return sonu. string olarak return edilir.
     */
    public String toString() {
        String result = "";
        for(int i = 0; i < solution.length; i++){
            result +=  ("--->" + solution[i] + "\n");
        }
        result += "\n";
        return result;
    }
}
