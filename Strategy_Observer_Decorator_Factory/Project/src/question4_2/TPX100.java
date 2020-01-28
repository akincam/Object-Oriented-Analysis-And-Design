package question4_2;

/**
 * Plane sınıfından extend edılmıstır.
 * TPX 100 modelidir.
 * @author Akin Cam
 */
public class TPX100 extends Plane {
    /**
     * Komponentlarının hangı fabrıkada hangı tipte uretılecegını belırlemek ıcın tutulur.
     */
    PlaneIngrFactory planeComponentFactory;

    /**
     * Hangi fabrikada uretileceği belirlenir.
     * @param planeComponentFactory Uretılecek fabrıka
     */
    public TPX100(PlaneIngrFactory planeComponentFactory) {
        this.planeComponentFactory = planeComponentFactory;
    }
    /**
     * TPX100 model ucak ıcın bılgıler bulunmaktadır.
     */
    void prepare() {
        model           = "TPX100";
        purpose         = "Domestic flights";
        skeleton        = "Aluminum alloy";
        engine          = "Single jet engine";
        seats           = 50;
        seatingCover    = planeComponentFactory.createSeating();
        engineInjection = planeComponentFactory.createEngineInjection();
    }
}
