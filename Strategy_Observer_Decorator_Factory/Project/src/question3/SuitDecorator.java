package question3;

/**
 * Suits sınıfundan extends edılmıs olup decorator ıcın olusturulmustur
   * Suıt uzerıne bırden cok aksesuar eklemek ıcın olusturulmus decorator desıgn pattern desenını desteklemeye yarayan sınıftır
 * @author Akin Cam
 */
public abstract class SuitDecorator extends Suit{
    /**
     * Decorator yapısı ıcın öncekı ozellıklerı eklemek ıcın kullanılır.
     */
    Suit suit;

    /**
     * aksesuar tanımı ıcın kullanılır.
     * @return aksesuar tanımı return eder.
     */
    public abstract String getDescription();
}
