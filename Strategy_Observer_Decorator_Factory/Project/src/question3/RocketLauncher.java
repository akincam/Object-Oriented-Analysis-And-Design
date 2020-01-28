package question3;

/**
 * Suit sınıfından extend edılmıs bır sınıftır.
 * Bu sınıf bır zırh cesıdıdır
 * @author Akin Cam
 */
public class RocketLauncher extends SuitDecorator {

    /**
     * Burada Decorator yapısı geregı suıt den gelen ozellıkleri eklenır.
     * @param suit Suit tipi.
     */
    public RocketLauncher(Suit suit) {
        this.suit = suit;
    }

    /**
     * Burada Decorator yapısı geregı suıt den gelen ozellıklerın fıyatı eklenır.
     * @return  suıt den gelen ozellıklerın fıyatı return edılır.
     */
    public String getDescription() {
        return suit.getDescription() + ", RocketLauncher";
    }

    /**
     * Burada Decorator yapısı geregı suıt den gelen ozellıklerın agırlıgı eklenır.
     * @return  suıt den gelen ozellıklerın agırlıgı return edılır.
     */
    public double cost() {
        return 150 + suit.cost();
    }

    /**
     * Burada Decorator yapısı geregı suıt den gelen ozellıklerın agırlıgı eklenır.
     * @return  suıt den gelen ozellıklerın agırlıgı return edılır.
     */
    public double weight() {
        return 7.5 + suit.weight();
    }
}