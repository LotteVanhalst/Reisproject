package be.kdg.reisproject.data;

import be.kdg.reisproject.model.Reis;
import be.kdg.reisproject.model.Werelddeel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lotte Vanhalst
 * @version 1.0 1/04/2019 13:20
 */
public class Data {
    private static List<Reis> reizen;

    public Data() {
        reizen = new ArrayList<>();
    }

    public static List<Reis> geefReizen (){
        reizen.add(new Reis("Argentinië", Werelddeel.AMERIKA, "21/07/2019", 22, 3450.49, "Spaans", "Peso", 15, 300));
        reizen.add(new Reis("Bolivië", Werelddeel.AMERIKA, "15/11/2019", 18, 2199.59, "Spaans", "Boliviano ", 16, 200));
        reizen.add(new Reis("Kroatië", Werelddeel.EUROPA, "17/06/2020", 10, 1510.10, "Kroatisch", "Kuna", 15, 250));
        reizen.add(new Reis("Ijsland", Werelddeel.EUROPA, "21/07/2019", 15, 3256.98, "Ijslands", "Kroon", 14, 450));
        reizen.add(new Reis("Indonesië", Werelddeel.AZIE, "5/07/2019", 21, 2456.78, "Indonesisch", "Indonesische Rupiah", 33, 300));
        reizen.add(new Reis("Australië", Werelddeel.OCEANIE, "10/10/2019", 28, 5678.99, "Engels", "Australische Dollar", 22, 650));
        reizen.add(new Reis("Vietnam", Werelddeel.AZIE, "15/03/2020", 16, 1876.87, "Vietnamees", "Dong", 23, 250));
        reizen.add(new Reis("Zuid-Afrika", Werelddeel.AFRIKA, "22/11/2019", 22, 2987.78, "Zuid-Afrikaans en Engels", "Rand", 26, 300));
        reizen.add(new Reis("Namibië", Werelddeel.AFRIKA, "15/02/2020", 18, 2876.45, "Engels", "Namibische Dollar", 29, 350));
        reizen.add(new Reis("Zweden", Werelddeel.EUROPA, "15/08/2019", 10, 1567.34, "Zweeds", "Zweedse Kroon", 21, 200));
        reizen.add(new Reis("Jordanië", Werelddeel.AZIE, "11/09/2019", 8, 1235.76, "Arabisch", "Jordaanse Dinar", 29, 150));
        reizen.add(new Reis("Schotland", Werelddeel.EUROPA, "25/05/2020", 10, 1365.23, "Engels", "Pond Sterling", 14, 175));
        reizen.add(new Reis("Canada", Werelddeel.AMERIKA, "10/06/2020", 25, 4367.87, "Engels en Frans", "Canadese Dollar", 24, 500));
        reizen.add(new Reis("Costa Rica", Werelddeel.AMERIKA, "20/09/2019", 15, 2654.34, "Spaans", "Costa Ricaanse Colon", 27, 300));
        reizen.add(new Reis("Spanje", Werelddeel.EUROPA, "15/04/2020", 8, 658.76, "Spaans", "Euro", 20, 150));
        return reizen;
    }
}

