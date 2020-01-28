package question4_1;

import java.util.ArrayList;

/**
 * Ucak sınıfı.
 * Ucagın modelı, amacı, ıskeletı ve motoru ıceren bılgıler bulunmaktadır.
 * @author Akin Cam
 */
public abstract class Plane {
    /**
     * ucak modeli
     */
    String model;
    /**
     * yapılıs amacı
     */
    String purpose;
    /**
     * ucak iskeleti
     */
    String skeleton;
    /**
     * ucak motoru
     */
    String engine;
    /**
     * ucak koltuk sayısı
     */
    int seats;
    /**
     * Ucagın modelını return eder
     * @return ucak modelı
     */
    public String getModel() {
        return model;
    }

    /**
     * Ucagın yapılıs amacını return eder
     * @return ucak yapılıs amacı
     */
    public String getPurpose() {
        return purpose;
    }

    /**
     * Ucagın ıskelet yapısını return eder.
     * @return ucak ıskelet yapısı
     */
    public String getSkeleton() {
        return skeleton;
    }

    /**
     * Ucagın motorunu return eder.
     * @return ucak motoru
     */
    public String getEngine() {
        return engine;
    }

    /**
     * Ucagın koltuk sayısını return eder
     * @return ucak koltuk sayısı
     */
    public int getSeats() {
        return seats;
    }

    /**
     * ıskelet olusturma
     */
    public void constructSkeleton(){
        System.out.println(this.getSkeleton()+ " skeleton is constructing");
    }

    /**
     * motoru yerlestırme
     */
    public void placeEngines(){
        System.out.println(this.getEngine()+" is placing");
    }

    /**
     * koltukları yerlestırme
     */
    public void placeSeats(){
        System.out.println(this.getSeats()+" is placing");
    }

    /**
     * kullanıcıya ekranda ucak bılgısını  göstermeyı saglar
     * @return ucak bılgısı.
     */
    public String toString() {
        StringBuffer display = new StringBuffer();
        display.append("---- " + this.getModel() + " ----\n"+this.getPurpose() + "\n"+this.getEngine()+
                "\n"+this.getSkeleton()+ "\n"+this.getSeats() + "\n");

        return display.toString();
    }
}
