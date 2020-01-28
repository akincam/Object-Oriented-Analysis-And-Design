package question4_2;

/**
 * PlaneIngrFactory interface sınıfından ımplement edılmıstır.
 * @author Akin Cam
 */
public class EurasiaPlaneComponentFactory implements PlaneIngrFactory {
    /**
     * Koltuk tıplerını olusturur.
     * @return LinenSeatingCover() return  eder Eurasia ıcın
     */
    public SeatingCover createSeating() {
        return new LinenSeatingCover();
    }

    /**
     * EngineInjection olusturur.
     * @return TurbofanEngineInjection() return  eder Eurasia ıcın
     */
    public EngineInjection createEngineInjection() {
        return new TurbofanEngineInjection();

    }
}
