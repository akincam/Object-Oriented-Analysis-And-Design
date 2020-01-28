package question4_1;

/**
 * Uretılen ucak tıpleridir.
 * @author Akin Cam
 */
public class PlanesFactory {
    /**
     * Farklı modellerde ucak uretımı gerceklestırılebılır..
     * @param type ucak tıpı.
     * @return uretılen ucak modelı olusturulur, return edılır.
     */
    public Plane buildPlane(String type) {
        Plane plane = null;

        if (type.equals(Type.TPX100.toString())) {
            plane = new TPX100();
        } else   if (type.equals(Type.TPX200.toString())) {
            plane = new TPX200();
        } else {
            plane = new TPX300();
        }
        return plane;
    }
}
