package question4_1;

/**
 * Plane sınıfından extend edılmıstır.
 * TPX 100 modelidir.
 * @author Akin Cam
 */
public class TPX100 extends Plane {

    /**
     * TPX100 model ucak ıcın bılgıler bulunmaktadır.
     */
    public TPX100() {
        model       = "TPX100";
        purpose     = "Domestic flights";
        skeleton    = "Aluminum alloy";
        engine      = "Single jet engine";
        seats       = 50;
    }
}
