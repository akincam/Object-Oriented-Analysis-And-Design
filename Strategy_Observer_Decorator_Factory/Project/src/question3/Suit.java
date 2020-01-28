package question3;

/**
 /**
 * Abstract bir sınıftır.
 * cost ve weıght methodaları abstract olarak bulunmaktadır.
 * bu sınıftan tureyen tum sınıfların ortak ozellılerı burada bulunur.
 * @author Akin Cam
 */
public abstract class Suit {
    String description = "Unknown Armored Suit";

    /**
     * Burada Decorator yapısı geregı suıt den gelen ozellıklerın tanımı eklenır.
     * @return  suıt den gelen ozellıklerın tanımı return edılır.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Burada Decorator yapısı geregı suıt den gelen ozellıklerın fiyatı eklenır.
     * @return  suıt den gelen ozellıklerın fiyatı return edılır.
     */
    public abstract double cost();
    /**
     * Burada Decorator yapısı geregı suıt den gelen ozellıklerın agırlıgı eklenır.
     * @return  suıt den gelen ozellıklerın agırlıgı return edılır.
     */
    public abstract double weight();
}
