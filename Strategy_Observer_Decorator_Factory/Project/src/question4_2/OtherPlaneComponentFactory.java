package question4_2;

/**
 * PlaneIngrFactory interface sınıfından ımplement edılmıstır.
 * @author Akin Cam
 */
public class OtherPlaneComponentFactory implements PlaneIngrFactory {
    /**
     * Koltuk tıplerını olusturur.
     * @return VelvetSeatingCover() return  eder Digerlerı ıcın
     */
    public SeatingCover createSeating() {
        return new LeatherSeatingCover();
    }

    /**
     * EngineInjection olusturur.
     * @return TurbojetEngineInjectio() return  eder Digerlerı ıcın
     */
    public EngineInjection createEngineInjection() {
        return new GearedTurbofanEngineInjection();
    }
}
