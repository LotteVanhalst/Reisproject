package be.kdg.reisproject;

import be.kdg.reisproject.model.Reis;
import be.kdg.reisproject.model.Werelddeel;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.logging.LogManager;

/**
 * @author Lotte Vanhalst
 * @version 1.0 1/04/2019 15:45
 */
public class Demo_4 {

    public static void main(String[] args) {
        URL configURL = Demo_4.class.getResource("/logging.properties");
        if (configURL != null) {
            try (InputStream is = configURL.openStream()) {
                LogManager.getLogManager().readConfiguration(is);
            } catch (IOException e) {
                System.err.println("Configuratiebestand is corrupt!");
            }
        } else {
            System.out.println("Configuratiebestand niet gevonden!");
        }

        Reis reis1 = new Reis("Argentinië", Werelddeel.AMERIKA, "01/01/2019", 22, 3450.49, "Spaans", "Peso", 15, 300);
        Reis reis2 = new Reis("Bolivië", Werelddeel.AMERIKA, "15/11/2019", 35, 2199.59, "Spaans", "Boliviano ", 16, 200);
        Reis reis3 = new Reis("Kroatië", Werelddeel.EUROPA, "17/06/2019", 10, 365.45, "Kroatisch", "Kuna", 15, 250);
        Reis reis4 = new Reis("Ijsland", Werelddeel.EUROPA, "21/07/2019", 15, 3256.98, "", "Kroon", 14, 450);
        Reis reis5 = new Reis("Indonesië", Werelddeel.AZIE, "5/07/2019", 21, 2456.78, "Indonesisch", "Indonesische Rupiah", 76, 300);

    }
}
