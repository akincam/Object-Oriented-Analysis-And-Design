package question1;

/**
 * Hangi metodlarin secilebilegini enum yapisinda belirler.
 * @author Akin Cam
 */
public enum  Types {
    GaussElimination , MatrixInversion;

    @Override
    public String toString() {
        String return_type=null;
        switch (this){
            case GaussElimination:
                return_type = "Gauss Elimination";
                break;
            case MatrixInversion:
                return_type = "Matrix Inversion";
                break;
        }
        return return_type;
    }
    public static int size = Types.values().length;
}