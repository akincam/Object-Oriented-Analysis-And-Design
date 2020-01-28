package question1;

/**
 * Matris Inversıon Methodu ıle lıneer denklen cozumu yapılır.
 * @author Akin Cam
 */
public class MatrixInversion  extends DetermineSolutionType implements SolverMethods {
    /**
     * matrisin tersini tutan array
     */
    double [][] inverseArray;
    /**
     * Denklem sonucu mevcut ise bu degıskende tutulur.
     */
    private double [] solution;

    /**
     * Bir birim matrıs olusuturulur. Elde bulunan ınput matrısı bırım matrısı yapılır.
     * Bu matrıse uygulanan tum ıslemler bırım matrısede uygulanır.
     * Bırım matrıs ıslemler sonunda ana matrısın tersı olur.
     * Bu ıslem ıcın ust ucgen matrıs ve alt ucgen matrıs olusturulur.
     * @param matrix ınput olarak verilen matrısın Ax+Bx bolumu
     */
    private void inverse(double[][] matrix){
        for(int i=1;i<matrix.length;i++){ //ust ucgen matrıs
            for(int k=0;k<i;k++) {
                double pivot=(matrix[i][k]/matrix[k][k]);
                for (int j = 0; j < matrix[0].length; j++) {
                    matrix[i][j]        += -matrix[k][j] * pivot;
                    inverseArray[i][j]+= -inverseArray[k][j] * pivot;
                }
            }
        }
        for(int i=matrix.length-2;i>=0;i--){ //alt ucgen matrıs
            for(int k=matrix.length-1;k>i;k--) {
                double pivot=(matrix[i][k]/matrix[k][k]);
                for (int j = matrix.length-1; j >=0;j--) {
                    matrix[i][j] += -matrix[k][j] * pivot;
                    inverseArray[i][j]+= -inverseArray[k][j] * pivot;
                }
            }
        }
    }

    /**
     * Matrısın tersını almak ıcın bırım matrıs olusturulur
     * @param matrix bırım matrıs olusuturulacak array
     */
    private void unitMatrix(double [][] matrix){
        for(int i=0;i<matrix.length;i++)
            for(int j=0;j<matrix.length;j++){
                if(i==j)
                    inverseArray[i][j]=1;
                else
                    inverseArray[i][j]=0;
            }
    }

    /**
     * Ilk olarak matrısın tersı olup olmadıgına bakılır.
     * Eger var ıse ust ve alt ucgen matrıs olusturulur.
     * Daha sonra kosegende bulunan sayılar kendılerıne bolunur ve matrısın tersı elde edılır.
     * @param matrix Ax = b yapısının sag tarafı
     * @param vector Ax = b yapısının sol tarafı
     */
    public void   solver(double[][] matrix, double[] vector) {
        boolean boo=false;
        inverseArray = new double[matrix.length][matrix.length];
        boo = hasMatrixInversion(matrix); //matrısın tersı olup olmadıgı kontrole edılır.
        if(!boo){
            Gui.printResult("Matrisin Tersi Bulunmamaktadır");
            return;
        }
        unitMatrix(matrix);
        inverse(matrix);

        for(int i=0;i<matrix.length;i++){ //kosegende bulunan rakamların 1 olması ıcın kendısıne bolunur.
            for(int ii = 0; ii< inverseArray.length; ii++){
                inverseArray[i][ii]= inverseArray[i][ii] /matrix[i][i];
            }
            matrix[i][i] =  matrix[i][i]/ matrix[i][i];
        }

        solution = new double[matrix.length];
        for(int i = 0; i< inverseArray.length; i++){//son olarak matrıs ve vector carpılıp sonuc elde edılır.
            for(int j = 0; j< inverseArray.length; j++){
                solution[i]+= inverseArray[i][j]*vector[j];
            }
        }

        SolutionTypes st = setSolutionIsValid(solution);
        if(st.toString().equals(SolutionTypes.Single.toString()))
            Gui.printResult("Solution is-->\n"+this.toString());
        else
            Gui.printResult("Solution is-->\n"+st.toString());
    }

    /**
     * matrısın son satırında tum degerler 0 ıse matrısın tersı yoktur ve bu durum kontrol edılır.
     * @param matrix ınput matrısı
     * @return eger matrısın tersı var ıse true yoksa false dondurulur
     */
    public boolean hasMatrixInversion(double [][] matrix){
        for(int j=0;j<matrix.length;j++){
            if(matrix[matrix.length-1][j] != 0)
                return true;
        }
        return false;
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
