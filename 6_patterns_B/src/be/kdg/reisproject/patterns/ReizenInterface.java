package be.kdg.reisproject.patterns;

import be.kdg.reisproject.model.Reis;

import java.util.List;
import java.util.TreeSet;

/**
 * @author Lotte Vanhalst
 * @version 1.0 10/04/2019 0:23
 */
public interface ReizenInterface {
    TreeSet<Reis> getBeschikbareReizen();

    void voegToe(Reis reis);

    void verwijder(String naam, String begindatum, int aantalDagen);

    Reis zoek(String naam, String begindatum, int aantalDagen);

    List<Reis> gesorteerdOpKostprijs();

    List<Reis> gesorteerdOpDatum();

    List<Reis> gesorteerdOpDuur();

    int getAantal();
}
