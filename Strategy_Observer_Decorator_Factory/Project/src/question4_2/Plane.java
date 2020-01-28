package question4_2;

import java.util.ArrayList;

public abstract  class Plane {
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
     * Ucak motor enjeksiyon tipi
     */
    EngineInjection engineInjection;
    /**
     * koltuk kaplama tipi
     */
    SeatingCover seatingCover;


    abstract void prepare();

    public String getModel() {
        return model;
    }

    public String getPurpose() {
        return purpose;
    }

    public String getSkeleton() {
        return skeleton;
    }

    public String getEngine() {
        return engine;
    }

    public int getSeats() {
        return seats;
    }

    public EngineInjection getEngineInjection() {
        return engineInjection;
    }

    public SeatingCover getSeatingCover() {
        return seatingCover;
    }

    public void constructSkeleton(){
        System.out.println(this.getSkeleton()+ " skeleton is constructing");
    }

    public void placeEngines(){
        System.out.println(this.getEngine()+" is placing");
    }

    public void placeSeats(){
        System.out.println(this.getSeats()+" is placing");
    }

    public String toString() {
        StringBuffer display = new StringBuffer();
        display.append("---- " + this.getModel() + " ----\n"+this.getPurpose() + "\n"+this.getEngine()+
                "\n"+this.getSkeleton()+ "\n"+this.getSeats() + "\n");
        display.append(this.getSeatingCover()+"\n");
        display.append(this.getEngineInjection()+"\n");
        return display.toString();
    }
}
