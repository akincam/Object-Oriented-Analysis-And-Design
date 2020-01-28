package part2;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Akin Cam
 * Matrix includes Complex Number.
 */
public class Matrix {

    /**
     * Square matrix
     *Complex Number array
     */
    private ComplexNumber [][] matrix;

    /**
     *Constructor
     * initialize Matrix
     * @param length of matrix
     */
    public Matrix(int length){
        matrix = new ComplexNumber[length][length];
        for(int i=0;i<matrix.length;i++)
            for(int j=0;j<matrix.length;j++)
                matrix[i][j] = new ComplexNumber();
    }

    /**
     * Returns matrix
     * @param row num
     * @param column num
     * @return matrix
     */
    public ComplexNumber get(int row,int column) {
        return matrix[row][column];
    }

    /**
     * Returns matrix
     * @return matrix
     */
    public ComplexNumber[][] getMatrix() {
        return matrix;
    }

    /**
     *
     * @param matrix input copied into matrix
     */
    public void copyMatrix(ComplexNumber[][] matrix) {
        System.arraycopy(matrix,0,this.matrix,0,matrix.length);
    }

    /**
     *
     * @return length of the matrix
     */
    public int length(){
        return matrix.length;
    }

    /**
     *fills the matrix randomly(real and imaginary)
     */
    public void fillMatrix(){
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[i].length;j++){
                matrix[i][j] = new ComplexNumber(ThreadLocalRandom.current().nextInt(-100,100),ThreadLocalRandom.current().nextInt(-100,100));
            }
        }
    }

    /**
     * overrided toStringMethod
     * @return string value of matrix
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(ComplexNumber [] complexNumbers : matrix) {
            for (ComplexNumber complexNum : complexNumbers) {
                sb.append("|"+complexNum.getReal()+"+"+complexNum.getImaginary()+"i|,   ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
