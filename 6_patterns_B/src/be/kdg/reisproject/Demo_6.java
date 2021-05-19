package be.kdg.reisproject;

import be.kdg.reisproject.model.Reis;
import be.kdg.reisproject.model.Reizen;
import be.kdg.reisproject.model.Werelddeel;
import be.kdg.reisproject.patterns.UnmodifiableReizen;

/**
 * @author Lotte Vanhalst
 * @version 1.0 10/04/2019 15:06
 */
public class Demo_6 {
    public static void main(String[] args) {
        Reizen reizen = new Reizen();
        reizen.voegToe(new Reis("Bolivië", Werelddeel.AMERIKA, "15/11/2019", 18, 2199.59, "Spaans", "Boliviano ", 16, 200));
        reizen.voegToe(new Reis("Zweden", Werelddeel.EUROPA, "15/08/2019", 10, 1567.34, "Zweeds", "Zweedse Kroon", 21, 200));
        reizen.voegToe(new Reis ("Spanje", Werelddeel.EUROPA, "15/04/2020", 8, 658.76, "Spaans", "Euro", 20, 150));
        reizen.voegToe(new Reis("Indonesië", Werelddeel.AZIE, "5/07/2019", 21, 2456.78, "Indonesisch", "Indonesische Rupiah", 33, 300));
        reizen.voegToe(new Reis("Zuid-Afrika", Werelddeel.AFRIKA, "22/11/2019", 22, 2987.78, "Zuid-Afrikaans en Engels", "Rand", 26, 300));

        UnmodifiableReizen beschermdeReizen = new UnmodifiableReizen(reizen);
        /*System.out.println("De volgende reizen zijn beschikbaar: ");
        for (Reis reis: beschermdeReizen.getBeschikbareReizen()){
            System.out.println(reis.toString());
        }
        System.out.println();
        System.out.println("Het aantal beschikbare reizen: " + beschermdeReizen.getAantal());
        System.out.println();
        beschermdeReizen.voegToe(new Reis("Jordanië", Werelddeel.AZIE, "11/09/2019", 8, 1235.76, "Arabisch", "Jordaanse Dinar", 29, 150));
        System.out.println("Het aantal beschikbare reizen na toevoegen: " + beschermdeReizen.getAantal());
        System.out.println();
        beschermdeReizen.verwijder("Bolivië", "15/11/2019", 18);
        System.out.println("Het aantal beschikbare reizen na verwijderen: " + beschermdeReizen.getAantal());
        System.out.println();*/

        System.out.println("De volgende reizen zijn beschikbaar na sorteren op kostprijs: ");
        for (Reis reis: beschermdeReizen.gesorteerdOpKostprijs()){
            System.out.println(reis.toString());
        }
        System.out.println("Het aantal beschikbare reizen: " + beschermdeReizen.getAantal());
        System.out.println();
        beschermdeReizen.voegToe(new Reis("Jordanië", Werelddeel.AZIE, "11/09/2019", 8, 1235.76, "Arabisch", "Jordaanse Dinar", 29, 150));
        System.out.println("Het aantal beschikbare reizen na toevoegen: " + beschermdeReizen.getAantal());
        System.out.println();
        beschermdeReizen.verwijder("Bolivië", "15/11/2019", 18);
        System.out.println("Het aantal beschikbare reizen na verwijderen: " + beschermdeReizen.getAantal());
        System.out.println();

        System.out.println("De volgende reizen zijn beschikbaar na sorteren op datum: ");
        for (Reis reis: beschermdeReizen.gesorteerdOpDatum()){
            System.out.println(reis.toString());
        }
        System.out.println("Het aantal beschikbare reizen: " + beschermdeReizen.getAantal());
        System.out.println();
        beschermdeReizen.voegToe(new Reis("Jordanië", Werelddeel.AZIE, "11/09/2019", 8, 1235.76, "Arabisch", "Jordaanse Dinar", 29, 150));
        System.out.println("Het aantal beschikbare reizen na toevoegen: " + beschermdeReizen.getAantal());
        System.out.println();
        beschermdeReizen.verwijder("Bolivië", "15/11/2019", 18);
        System.out.println("Het aantal beschikbare reizen na verwijderen: " + beschermdeReizen.getAantal());
        System.out.println();

        System.out.println("De volgende reizen zijn beschikbaar na sorteren op duur: ");
        for (Reis reis: beschermdeReizen.gesorteerdOpDuur()){
            System.out.println(reis.toString());
        }
        System.out.println("Het aantal beschikbare reizen: " + beschermdeReizen.getAantal());
        System.out.println();
        beschermdeReizen.voegToe(new Reis("Jordanië", Werelddeel.AZIE, "11/09/2019", 8, 1235.76, "Arabisch", "Jordaanse Dinar", 29, 150));
        System.out.println("Het aantal beschikbare reizen na toevoegen: " + beschermdeReizen.getAantal());
        System.out.println();
        beschermdeReizen.verwijder("Bolivië", "15/11/2019", 18);
        System.out.println("Het aantal beschikbare reizen na verwijderen: " + beschermdeReizen.getAantal());
        System.out.println();
    }

}
