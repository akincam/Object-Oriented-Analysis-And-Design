package question3;

/**
 * Suit sınıfından extend edılmıs bır sınıftır.
 * Bu sınıf bır zırh cesıdıdır
 * @author Akin Cam
 */
public class Ora extends Suit {

    /**
     * Burada Decorator yapısı geregı suıt den gelen ozellıkleri eklenır.
     */
    public Ora(){
        this.description="Ora";
    }
    /**
     * Burada Decorator yapısı geregı suıt den gelen ozellıklerın fıyatı eklenır.
     * @return  suıt den gelen ozellıklerın fıyatı return edılır.
     */
    @Override
    public double cost() {
        return 1500;
    }
    /**
     * Burada Decorator yapısı geregı suıt den gelen ozellıklerın agırlıgı eklenır.
     * @return  suıt den gelen ozellıklerın agırlıgı return edılır.
     */
    @Override
    public double weight() {
        return 30;
    }
}