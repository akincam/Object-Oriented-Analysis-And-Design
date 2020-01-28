package question4_1;

/**
 * Ucakların uretım asamasını gmsterır.
 * Factory objesının bulundurur.
 * Bu sayede ucak ınsa edılır.
 * @author Akin Cam
 */
public class PlaneStore {
    /**
     * Factory design mattern icin ucagı ınsa etmeyı saglar.
     */
    PlanesFactory factory;

    /**
     * verılen factory metodu set edılır.
     * @param factory PlanesFactory objesı
     */
    public PlaneStore(PlanesFactory factory) {
        this.factory = factory;
    }

    /**
     * ucagı olusturur.
     * @param type Hangı tıo ucak ınsa edılecegı
     * @return ınsa edılmıs ucagı dondurur
     */
    public Plane createPlane(Type type) {
        Plane plane;
        plane = factory.buildPlane(type.toString());
        plane.constructSkeleton();
        plane.placeEngines();
        plane.placeSeats();
        return plane;
    }

}
