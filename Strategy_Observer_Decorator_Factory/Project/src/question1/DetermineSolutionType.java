package question1;

/**
 * Elde edilen output dan sonucun var olup olmadığı var ise tek ya da sonsuz cozun oldugunu belirler
 *@author Akin Cam
 */
public abstract class DetermineSolutionType {

    /**
     * Verilen sonuc eger sonsuz deger(Infinity) ıcerıyorsa sonsuz sonuc
     * double degerını asan degerler(Nan) ıcerıyorsa cozum olmadıgı anlamına gelır
     * @param matrix elde edılen test edılmek uzere bulunan output
     * @return sonuc tıpını return eder
     */
    public SolutionTypes setSolutionIsValid(double[] matrix) {
        int counter = 0;
        int infinity = 0;
        for (int j = 0; j < matrix.length; j++) {
            if (Double.isInfinite(matrix[j]))
                infinity++;
        }
        if(counter == matrix.length) {
            return SolutionTypes.NoSolution;
        }
        else if(infinity>0)
            return SolutionTypes.Infinity;
        else
            return SolutionTypes.Single;
    }
}
