package question1;

/**
 * Verilen denklem sonucunda olusan sonuclar bu enum yapisinda belirlenır.
 * @author Akin Cam
 */
public enum  SolutionTypes {
    Single , Infinity , NoSolution;

    /**
     * Enum type to string methodu
     * @return String
     */
    @Override
    public String toString() {
        String return_type;
        switch (this){
            case Single:
                return_type = "SINGLE";
                break;
            case Infinity:
                return_type = "INFINITY";
                break;
            case NoSolution:
                return_type = "NOSOLUTION";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + this);
        }
        return return_type;
    }
}
