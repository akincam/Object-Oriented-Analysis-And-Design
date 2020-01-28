package question3;

/**
 * Suit sınıfından extend edılmıs bır sınıftır.
 * Bu sınıf bır zırh cesıdıdır
 * @author Akin Cam
 */
public class Tor extends Suit {

    public Tor(){
        this.description="Tor";
    }
    @Override
    public double cost() {
        return 5000;
    }

    @Override
    public double weight() {
        return 50;
    }
}