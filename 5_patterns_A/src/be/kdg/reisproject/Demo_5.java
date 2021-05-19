package be.kdg.reisproject;

import be.kdg.reisproject.model.Reis;
import be.kdg.reisproject.model.ReisFactory;
import be.kdg.reisproject.model.Reizen;
import be.kdg.reisproject.model.Werelddeel;
import be.kdg.reisproject.patterns.ObservableReizen;
import be.kdg.reisproject.patterns.ReizenObserver;

/**
 * @author Lotte Vanhalst
 * @version 1.0 10/04/2019 0:21
 */
public class Demo_5 {
    public static void main(String[] args) {
        ObservableReizen observableReizen = new ObservableReizen();
        ReizenObserver reizenObserver = new ReizenObserver(observableReizen);
        observableReizen.addObserver(reizenObserver);
        observableReizen.voegToe(ReisFactory.newFilledReis("Argentinië", Werelddeel.AMERIKA, "21/07/2019", 22, 3450.49, "Spaans", "Peso", 15, 300));
        observableReizen.verwijder("Argentinië", "21/07/2019", 22);

        System.out.println();

        System.out.println("Lege reizen:");
        System.out.println(ReisFactory.newEmptyReis());
        System.out.println();
        System.out.println("5 random reizen:");
        System.out.println("1) " + ReisFactory.newRandomReis());
        System.out.println("2) " + ReisFactory.newRandomReis());
        System.out.println("3) " + ReisFactory.newRandomReis());
        System.out.println("4) " + ReisFactory.newRandomReis());
        System.out.println("5) " + ReisFactory.newRandomReis());
    }
}
