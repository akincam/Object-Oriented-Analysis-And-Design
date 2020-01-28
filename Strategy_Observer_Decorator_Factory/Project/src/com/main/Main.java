package com.main;

import question1.Gui;
import question3.*;
import question4_1.Plane;
import question4_1.PlaneStore;
import question4_1.PlanesFactory;
import question4_1.Type;
import question4_2.DomesticPlaneStore;
import question4_2.EurasiaPlaneStore;

public class Main {

    public static void main(String[] args) {

        Gui gui = new Gui();
        System.out.println("\n--------------------------Question 3----------------------\n");

        Suit suit = new Dec();
        System.out.println(suit.getDescription()
                + " " + suit.cost()+"k TL"+" "+suit.weight()+" kg.");

        Suit suit2 = new Ora();
        suit2 = new Laser(suit2);
        suit2 = new Laser(suit2);
        suit2 = new Flamethrower(suit2);
        System.out.println(suit2.getDescription()
                + " " + suit2.cost()+"k TL"+" "+suit2.weight()+" kg.");
        Suit suit3 = new Tor();
        suit3 = new AutoRifle(suit3);
        suit3 = new Flamethrower(suit3);
        suit3 = new RocketLauncher(suit3);
        System.out.println(suit3.getDescription()
                + " " + suit3.cost()+"k TL"+" "+suit3.weight()+" kg.");

        System.out.println("\n--------------------------Question 4.1----------------------\n");
        PlanesFactory factory = new PlanesFactory();
        PlaneStore store = new PlaneStore(factory);

        Plane plane = store.createPlane(Type.TPX100);
        System.out.println("We create a plane model of " + plane.getModel() + "\n");
        System.out.println(plane);

        plane = store.createPlane(Type.TPX200);
        System.out.println("We create a plane model of " + plane.getModel() + "\n");
        System.out.println(plane);

        System.out.println("\n--------------------------Question 4.2----------------------\n");

        question4_2.PlaneStore nyStore = new DomesticPlaneStore();
        question4_2.PlaneStore nyStore2 = new EurasiaPlaneStore();
        question4_2.Plane plane1 = nyStore.buildPlane(Type.TPX300.toString());
        System.out.println( plane1.toString() + "\n");
        System.out.println("--------------------------");
        question4_2.Plane plane2 = nyStore2.buildPlane(Type.TPX100.toString());

        System.out.println( plane2.toString() + "\n");
    }
}
