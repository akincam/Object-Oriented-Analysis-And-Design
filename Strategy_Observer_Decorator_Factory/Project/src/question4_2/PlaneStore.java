package question4_2;

/**
 * Ucakların uretım asamasını gmsterır.
 * Factory objesının bulundurur.
 * Bu sayede ucak ınsa edılır.
 * @author Akin Cam
 */
public abstract  class PlaneStore {
    /**
     * ucagı ınsa eden methoddur
     * @param item ucak mode tıpı
     * @return hangi model ucagı uretecegını return eder.
     */
    protected abstract Plane createPlane(String item);

    /**
     * verilen tipte ucahı uretır
     * @param type ucak tipi
     * @return ucagı return eder
     */
    public Plane buildPlane(String type) {
        Plane plane = createPlane(type);
        if(plane!=null) {
            plane.prepare();
            System.out.println("|| Building a --> " + plane.getModel() + " ||");
            plane.constructSkeleton();
            plane.placeEngines();
            plane.placeSeats();
        }
        return plane;
    }
}
