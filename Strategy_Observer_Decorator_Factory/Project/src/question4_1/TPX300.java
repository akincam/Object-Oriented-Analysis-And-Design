package question4_1;

/**
 * Plane sınıfından extend edılmıstır.
 * TPX 300 modelidir.
 * @author Akin Cam
 */
public class TPX300 extends Plane {

    /**
     * TPX300 model ucak ıcın bılgıler bulunmaktadır.
     */
    public TPX300() {
        model       = "TPX300";
        purpose     = "Transatlantic flights";
        skeleton    = "Titanium alloy";
        engine      = "Quadro jet engines";
        seats       = 250;
    }
}
