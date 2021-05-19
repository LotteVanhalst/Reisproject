package be.kdg.reisproject.model;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Lotte Vanhalst
 * @version 1.0 1/05/2019 19:48
 */
public interface ReisDao {
    void voegToe(Reis reis);

    void verwijder(String naam, LocalDate begindatum, int aantalDagen);

    Reis zoek(String naam, LocalDate begindatum, int aantalDagen);

    List<Reis> gesorteerdOpKostprijs();

    List<Reis> gesorteerdOpDatum();

    List<Reis> gesorteerdOpDuur();
}
