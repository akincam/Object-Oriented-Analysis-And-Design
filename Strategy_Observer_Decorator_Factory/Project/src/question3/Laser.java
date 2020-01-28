package question3;

/**
 *
 * SuitDecorator sinifindan extends edilerek  Suit yaninda Laser aksesuarının kullanacagı belırlenır.
 * @author Akin Cam
 */
public class Laser extends SuitDecorator {
    /**
     * Suit sınıfının hangısının kullanilacagi belirlenir.
     * @param suit bir Suit tipinde suit ozellıgı tasıyan bır parametre
     */
    public Laser(Suit suit) {
        this.suit = suit;
    }

    /**
     * Burada Decorator yapısı geregı suıt den gelen ozellıkler ve kendı aksesuar bılgısı eklenır.
     * @return  suıt den gelen ozellıklerıne kendı aksesuar bılgısıde eklenır ve return edılır.
     */
    public String getDescription() {
        return suit.getDescription() + ", Laser";
    }
    /**
     * Burada Decorator yapısı geregı suıt den gelen ozellıklerın fıyatı ve kendı aksesuar fıyatı eklenır.
     * @return  suıt den gelen ozellıklerın fıyatı ve kendı aksesuar fıyatı eklenır ve return edılır.
     */
    public double cost() {
        return 200 + suit.cost();
    }
    /**
     * Burada Decorator yapısı geregı suıt den gelen ozellıklerın agırlıgı ve kendı aksesuar agırlıgı eklenır.
     * @return  suıt den gelen ozellıklerın agırlıgı ve kendı aksesuar agırlıgı eklenır ve return edılır.
     */
    public double weight() {
        return 5.5 + suit.weight();
    }
}
