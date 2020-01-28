package question4_2;

/**
 * PlaneStore dan extend edılmıs Ucak Magazası
 * @author Akin Cam
 */
public class EurasiaPlaneStore extends PlaneStore {
    /**
     * Yerlı ucak ureten magaza methodu
     * @param item Hangi tıp Yerli Olmayan ucak uretileceğini alır
     * @return uretilecek ucak modeli döndurulur.
     */
    protected Plane createPlane(String item) {
        Plane plane = null;
        PlaneIngrFactory pcf = new EurasiaPlaneComponentFactory();

        if(item.equals(Type.TPX100.toString())){
            plane = new TPX100(pcf);
        }else if(item.equals(Type.TPX200.toString())){
            plane = new TPX200(pcf);
        }else{
            plane = new TPX300(pcf);
        }
        return plane;
    }
}
