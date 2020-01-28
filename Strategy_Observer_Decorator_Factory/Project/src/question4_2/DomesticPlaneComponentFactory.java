package question4_2;

/**
 * PlaneIngrFactory interface sınıfından ımplement edılmıstır.
 * @author Akin Cam
 */
public class DomesticPlaneComponentFactory implements PlaneIngrFactory {
    /**
     * Koltuk tıplerını olusturur.
     * @return VelvetSeatingCover() return  eder Domestıc ıcın
     */
    public SeatingCover createSeating() {
        return new VelvetSeatingCover();
    }

    /**
     * EngineInjection olusturur.
     * @return TurbojetEngineInjectio() return  eder Domestıc ıcın
     */
    public EngineInjection createEngineInjection() {
        return new TurbojetEngineInjection();
    }
}
