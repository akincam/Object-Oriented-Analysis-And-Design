package question4_2;

/**
 * Plane sınıfından extend edılmıstır.
 * TPX 100 modelidir.
 * @author Akin Cam
 */
public class TPX300 extends Plane {
    /**
     * Komponentlarının hangı fabrıkada hangı tipte uretılecegını belırlemek ıcın tutulur.
     */
    PlaneIngrFactory planeComponentFactory;
    /**
     * Hangi fabrikada uretileceği belirlenir.
     * @param planeComponentFactory Uretılecek fabrıka
     */
    public TPX300(PlaneIngrFactory planeComponentFactory) {
        this.planeComponentFactory = planeComponentFactory;
    }
    /**
     * TPX100 model ucak ıcın bılgıler bulunmaktadır.
     */
    void prepare() {
        model           = "TPX300";
        purpose         = "Transatlantic flights";
        skeleton        = "Titanium alloy";
        engine          = "Quadro jet engines";
        seats           = 250;
        seatingCover    = planeComponentFactory.createSeating();
        engineInjection = planeComponentFactory.createEngineInjection();
    }
}
