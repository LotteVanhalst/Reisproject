package be.kdg.reisproject;

import be.kdg.reisproject.data.Data;
import be.kdg.reisproject.model.Reis;
import be.kdg.reisproject.model.Werelddeel;
import be.kdg.reisproject.persist.ReisDbDao;

import java.time.LocalDate;

/**
 * @author Lotte Vanhalst
 * @version 1.0 1/05/2019 20:04
 */
public class Demo_8_JDBC {
    public static void main(String[] args) {
        Data lijst = new Data();
        ReisDbDao reisDbDao = null;
        try {
            reisDbDao = ReisDbDao.getInstance("8_persistentie/db/myDatabase");

           for (Reis reis: Data.geefReizen()) {
            reisDbDao.voegToe(reis);
            }

        Reis zoekReis = reisDbDao.zoek("Canada", LocalDate.of(2020,6,10), 25);
        System.out.println("\nZoek reis:");
        System.out.println(zoekReis);


        Reis nieuweReis = new Reis("Groenland", Werelddeel.AMERIKA, LocalDate.of(2020,6,10), 25, 4367.87, "Engels en Frans", "Canadese Dollar", 24, 500);

        reisDbDao.update(zoekReis, nieuweReis);

        Reis updateReis = reisDbDao.zoek("Groenland", LocalDate.of(2020,6,10), 25);
        System.out.println("\nNaam veranderd:");
        System.out.println(updateReis);

        reisDbDao.verwijder("Groenland", LocalDate.of(2020,6,10), 25);

        System.out.println("\nGesorteerd op kostprijs:");
        reisDbDao.gesorteerdOpKostprijs().forEach(System.out::println);

        System.out.println("\nGesorteerd op datum:");
        reisDbDao.gesorteerdOpDatum().forEach(System.out::println);

        System.out.println("\nGesorteerd op duur:");
            reisDbDao.gesorteerdOpDuur().forEach(System.out::println);

        System.out.println("\nReizen goedkoper dan 2000 euro:");
        reisDbDao.filter(2000.00).forEach(System.out::println);

        } catch (Exception ex){
            ex.printStackTrace();
        } finally {
            reisDbDao.close();
        }
    }
}
