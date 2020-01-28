package question4_2;

/**
 * PlaneStore dan extend edılmıs Yerli Ucak Magazası
 * @author Akin Cam
 */
public class DomesticPlaneStore extends PlaneStore {

    /**
     * Yerlı ucak ureten magaza methodu
     * @param item Hangi tıp ucak uretileceğini alır
     * @return uretilecek ucak modeli döndurulur.
     */
    protected Plane createPlane(String item) {
        Plane plane = null;
        PlaneIngrFactory pcf = new DomesticPlaneComponentFactory();

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
