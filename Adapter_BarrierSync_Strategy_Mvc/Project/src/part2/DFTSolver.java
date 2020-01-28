package part2;

/**
 * @author Akin Cam
 * implements Runnable class and calculates DFT.
 */
public  class DFTSolver implements Runnable {
    /**
     * Input matrix
     */
    public Matrix              matrix1;
    /**
     *Input matrix
     */
    public Matrix              matrix2;
    /**
     *return matrix
     */
    public Matrix              retMatrix;
    /**
     * matrix length
     */
    public int                 matrixLength;
    /**
     * total thread length
     */
    public int                 threadLength;
    /**
     * indexes to determine quarter of the matrix
     */
    public int  []             idx;
    /**
     *  determine quarter of the matrix
     */
    public int                 side;
    /**
     * to Barrier problem
     */
    public BarrierBehaviour barrierBehaviour;

    /**
     * solver type
     */
    public int                 type;

    /**
     * Constructor
     * Initializes variable and fills randomly
     * @param length matrix length
     * @param threadLength thread length
     * @param m1 Input matrix
     * @param m2 Input matrix
     * @param m3 return matrix
     * @param side quarter of matrix
     * @param type solution type which barrier selected
     * @param barrierBehaviour synchronized variable to barrier problem
     */
    public DFTSolver(int length, int threadLength, Matrix m1, Matrix m2, Matrix m3, int side, BarrierBehaviour barrierBehaviour,int type){
        this.matrixLength       = length;
        this.threadLength       = threadLength;
        this.matrix1            = new Matrix(matrixLength);
        this.matrix2            = new Matrix(matrixLength);
        this.retMatrix          = m3;
        this.side               = side;
        this.idx                = new int[threadLength];
        this.barrierBehaviour   = barrierBehaviour;
        this.type               = type;
        this.matrix1.copyMatrix(m1.getMatrix());
        this.matrix2.copyMatrix(m2.getMatrix());
    }

    /**
     *total thread length
     * @return total thread length
     */
    public int getThreadLength() {
        return threadLength;
    }

    /**
     * sums two matrix = m1 = m1+m2
     * @param idxes indexes to determine quarter of the matrix(startColumn,endColumn,startRow,endRow)
     */
    public void sumMatrices(int [] idxes){
        for(int i=idxes[0];i<idxes[1];i++){
            for(int j=idxes[2];j<idxes[3];j++){
                matrix1.getMatrix()[i][j].set(matrix1.getMatrix()[i][j].sum(matrix2.getMatrix()[i][j]));
            }
        }
    }

    /**
     * divides a matrix according to thread size and determines quartes like-
     * Thread 1 = 1
     * Thread 2 = 1 2
     * Thread 4 = 1 2
     *            3 4
     * @param threadSize thread size
     * @param side quarter of matrix
     * @param matrixLength matrix length
     * @return  (startColumn,endColumn,startRow,endRow)
     */
    public int [] setMatrixSide(int threadSize,int side,int matrixLength) {
        int returnV[] = new int[4];
        int odd;
        if(side % 2 !=0)
            odd = 1;
        else
            odd = 2;
        switch (threadSize) {
            case 1: {
                returnV[0] = 0;
                returnV[1] = matrixLength;
                returnV[2] = 0;
                returnV[3] = matrixLength;
                break;
            }
            case 2: {
                returnV[0] = matrixLength - matrixLength / side;
                returnV[1] = returnV[0] + matrixLength / 2;
                returnV[2] = 0;
                returnV[3] = matrixLength;
                break;
            }
            case 4: {
                int num;
                if(side == 1 || side == 2)
                    num = 1;
                else
                    num = 2;

                returnV[0] = matrixLength - matrixLength / num;
                returnV[1] = returnV[0] + matrixLength / 2;

                returnV[2] = matrixLength - matrixLength / odd;
                returnV[3] = returnV[2] + matrixLength / 2;

                break;
            }
            case 8: {
                if(side % 2 == 0){
                    returnV[0] = (matrixLength/4)*(side/2)-(matrixLength/4);
                    returnV[1]    = (matrixLength/4)*(side/2);
                }
                else{
                    int num =side+1;
                    returnV[0] = (matrixLength/4)*(num/2)-(matrixLength/4);
                    returnV[1]    = (matrixLength/4)*(num/2);
                }

                returnV[2] = matrixLength - matrixLength / odd;
                returnV[3] = returnV[2] + matrixLength / 2;
            }
        }
        return returnV;
    }

    /**
     * first two for traverse quarter of a matrix
     * second two fro traverse all matrix formula by dft.
     * @param matrix1 sum matrix
     * @param idxes (startColumn,endColumn,startRow,endRow)
     * @return dft matrix
     */
    public Matrix dftSolver(Matrix matrix1,int [] idxes){
        double real =0;
        double img =0;
        //quarter of matrix
        for(int i=idxes[0];i<idxes[1];i++){
            for(int j=idxes[2];j<idxes[3];j++){
                real = 0; img  = 0;
                //all matrix
                for(int v = 0;v<matrixLength;v++){
                    for(int z =0;z<matrixLength;z++){
                        real +=    (int)(matrix1.getMatrix()[v][z].getReal()*Math.cos(2.0*Math.PI*((1.0*j*z/(double)matrixLength)+(1.0*i*v/(double)matrixLength)))) /(double) Math.sqrt(matrix1.length()*matrix1.length());
                        img  -=    (int)(matrix1.getMatrix()[v][z].getImaginary()*Math.sin(2.0*Math.PI*((1.0*j*z/(double)matrixLength)+(1.0*i*v/(double)matrixLength)))) /(double)  Math.sqrt(matrix1.length()*matrix1.length());
                    }
                }
                //sets return value
                retMatrix.getMatrix()[i][j].setReal(real);
                retMatrix.getMatrix()[i][j].setImaginary(img);
            }
        }
        return retMatrix;
    }

    /**
     *This method solves this problem using no mutex and monitor.
     * first determines quarter side of thread
     * sum matrixes
     * according two type makes barrier syncronization
     * then calculate dft
     */
    public void solver(){
        try {
            idx= setMatrixSide(getThreadLength(),side,matrixLength);
            sumMatrices(idx);
            System.out.println(Thread.currentThread().getName()+" waiting on barrier");
            barrierBehaviour.waitThread();
            System.out.println(Thread.currentThread().getName()+" releases");
            dftSolver(matrix1,idx);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void setBarrierBehaviour(BarrierBehaviour bb) {
        barrierBehaviour = bb;
    }

    /**
     *Override run function
     */
    @Override
    public void run() {
        try {
            solver();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}
