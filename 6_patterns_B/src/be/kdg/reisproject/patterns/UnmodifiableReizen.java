package be.kdg.reisproject.patterns;

import be.kdg.reisproject.model.Reis;
import be.kdg.reisproject.model.Reizen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

/**
 * @author Lotte Vanhalst
 * @version 1.0 10/04/2019 15:08
 */
public class UnmodifiableReizen implements ReizenInterface {
    private Reizen reizen;

    public UnmodifiableReizen (Reizen reizen){
        this.reizen = reizen;
    }

    public TreeSet<Reis> getBeschikbareReizen(){
        return reizen.getBeschikbareReizen();
    }

    public void voegToe(Reis reis){
        throw new UnsupportedOperationException("Er kan aan deze reizen geen reis toegevoegd worden!");
    }

    public void verwijder(String naam, String begindatum, int aantalDagen){
        throw new UnsupportedOperationException("Er kan geen reis verwijderd worden van deze reizen!");
    }

    public Reis zoek(String naam, String begindatum, int aantalDagen){
       return reizen.zoek(naam, begindatum, aantalDagen);
    }

    public List<Reis> gesorteerdOpKostprijs(){
        reizen.gesorteerdOpKostprijs();
        List<Reis> reizenLijst = new ArrayList<>(reizen.gesorteerdOpKostprijs());
        return Collections.unmodifiableList(reizenLijst);
    }

    public List<Reis> gesorteerdOpDatum(){
        reizen.gesorteerdOpDatum();
        List<Reis> reizenLijst = new ArrayList<>(reizen.gesorteerdOpDatum());
        return Collections.unmodifiableList(reizenLijst);
    }

    public List<Reis> gesorteerdOpDuur(){
        reizen.gesorteerdOpDuur();
        List<Reis> reizenLijst = new ArrayList<>(reizen.gesorteerdOpDuur());
        return Collections.unmodifiableList(reizenLijst);
    }

    public int getAantal(){
        return reizen.getAantal();
    }
}
