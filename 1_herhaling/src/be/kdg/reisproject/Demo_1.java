package be.kdg.reisproject;

import be.kdg.reisproject.data.Data;
import be.kdg.reisproject.model.Reis;
import be.kdg.reisproject.model.Reizen;
import be.kdg.reisproject.model.Werelddeel;

import java.util.TreeSet;

/**
 * @author Lotte Vanhalst
 * @version 1.0 1/04/2019 15:45
 */
public class Demo_1 {

    public static void main(String[] args) {
        Data lijst = new Data();
        TreeSet<Reis> beschikbareReizen = new TreeSet();
        Reizen reizen = new Reizen();
        for (Reis reis: Data.geefReizen()){
            reizen.voegToe(reis);
        }
        for (Reis reis: reizen.getBeschikbareReizen()){
            System.out.println(reis.toString());
        }

        reizen.voegToe(new Reis("Bolivië", Werelddeel.AMERIKA, "15/11/2019", 18, 2199.59, "Spaans", "Boliviano ", 16, 200));

        reizen.verwijder("Argentinië", "21/07/2019", 22);

        System.out.println();
        for (Reis reis: reizen.getBeschikbareReizen()){
            System.out.println(reis.toString());
        }

        System.out.println();
        System.out.println(reizen.zoek("Zweden", "15/08/2019", 10));

        System.out.println(reizen.getAantal());

        System.out.println();
        System.out.println("Reizen gesorteerd op kostprijs:");
        for (Reis reis: reizen.gesorteerdOpKostprijs()){
            System.out.println(reis.toString());
        }

        System.out.println();
        System.out.println("Reizen gesorteerd op datum:");
        for (Reis reis: reizen.gesorteerdOpDatum()){
            System.out.println(reis.toString());
        }

        System.out.println();
        System.out.println("Reizen gesorteerd op aantal dagen:");
        for (Reis reis: reizen.gesorteerdOpDuur()){
            System.out.println(reis.toString());
        }

        System.out.println();
        try {
            Reis belgie = new Reis("België", Werelddeel.EUROPA, "01/01/2019", 8, 800, "Nederlands en Frans", "Euro", 7, 100);
        } catch (IllegalArgumentException exc){
            System.out.print(exc.getMessage());
        }
    }
}
