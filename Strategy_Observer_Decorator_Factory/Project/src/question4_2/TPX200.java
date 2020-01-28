package question4_2;
/**
 * Plane sınıfından extend edılmıstır.
 * TPX 100 modelidir.
 * @author Akin Cam
 */
public class TPX200 extends Plane {
    /**
     * Komponentlarının hangı fabrıkada hangı tipte uretılecegını belırlemek ıcın tutulur.
     */
    PlaneIngrFactory planeComponentFactory;

    /**
     * Hangi fabrikada uretileceği belirlenir.
     * @param planeComponentFactory Uretılecek fabrıka
     */
    public TPX200(PlaneIngrFactory planeComponentFactory) {
        this.planeComponentFactory = planeComponentFactory;
    }
    /**
     * TPX100 model ucak ıcın bılgıler bulunmaktadır.
     */
    void prepare() {
        model       = "TPX200";
        purpose     = "Domestic and short international flights";
        skeleton    = "Nickel alloy";
        engine      = "Twin jet engines";
        seats       = 100;
        seatingCover    = planeComponentFactory.createSeating();
        engineInjection = planeComponentFactory.createEngineInjection();
    }
}
