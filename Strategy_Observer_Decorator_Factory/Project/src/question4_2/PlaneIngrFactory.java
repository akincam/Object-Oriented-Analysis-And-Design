package question4_2;

/**
 * Fabrikalara gore degısen ucak component ozellıklerı
 * @author Akin Cam
 */
public interface PlaneIngrFactory {
    /**
     * Ucak koltuk kılıf tıpı
     * @return fabrıkaya gore uretılen koltup kılıfı
     */
    public SeatingCover createSeating();

    /**
     * Ucak engıne ınjectıon tıpı
     * @return fabrıkaya gore uretılen engıne ınjectıon
     */
    public EngineInjection createEngineInjection();

}
