package question4_1;

/**
 * Plane sınıfından extend edılmıstır.
 * TPX 200 modelidir.
 * @author Akin Cam
 */
public class TPX200 extends Plane {

    /**
     * TPX200 model ucak ıcın bılgıler bulunmaktadır.
     */
    public TPX200() {
        model       = "TPX200";
        purpose     = "Domestic and short international flights";
        skeleton    = "Nickel alloy";
        engine      = "Twin jet engines";
        seats       = 100;
    }
}
