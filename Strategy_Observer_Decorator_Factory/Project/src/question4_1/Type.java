package question4_1;

/**
 * Hangi modellerin secilebilegini belirleyen enum yapisi.
 * @author Akin Cam
 */
public enum  Type {
    TPX100 , TPX200 , TPX300;

    public String toString(){
        String return_type;
        switch (this){
            case TPX100:
                return_type = "TPX100";
                break;
            case TPX200:
                return_type = "TPX200";
                break;
            case TPX300:
                return_type = "TPX300";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + this);
        }
        return return_type;
    }
}
