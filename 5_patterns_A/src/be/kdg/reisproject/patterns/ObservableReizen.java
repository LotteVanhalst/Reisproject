package be.kdg.reisproject.patterns;

import be.kdg.reisproject.model.Reis;
import be.kdg.reisproject.model.Reizen;
import be.kdg.reisproject.model.ReizenInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.TreeSet;

/**
 * @author Lotte Vanhalst
 * @version 1.0 10/04/2019 0:25
 */
public class ObservableReizen extends Observable implements ReizenInterface {
    private Reizen reizen;

    public ObservableReizen (){
        reizen = new Reizen();
    }

    public TreeSet<Reis> getBeschikbareReizen(){
        return reizen.getBeschikbareReizen();
    }

    public void voegToe(Reis reis){
        reizen.voegToe(reis);
        setChanged();
        notifyObservers("Toegevoegd: " + reis);
    }

    public void verwijder(String naam, String begindatum, int aantalDagen){
        reizen.verwijder(naam, begindatum, aantalDagen);
        setChanged();
        notifyObservers("Verwijderd: " + naam);
    }

    public Reis zoek(String naam, String begindatum, int aantalDagen){
        return reizen.zoek(naam, begindatum, aantalDagen);
    }

    public List<Reis> gesorteerdOpKostprijs(){
        reizen.gesorteerdOpKostprijs();
        setChanged();
        notifyObservers();
        return reizen.gesorteerdOpKostprijs();
    }

    public List<Reis> gesorteerdOpDatum(){
        reizen.gesorteerdOpDatum();
        setChanged();
        notifyObservers();
        return reizen.gesorteerdOpDatum();
    }

    public List<Reis> gesorteerdOpDuur(){
        reizen.gesorteerdOpDuur();
        setChanged();
        notifyObservers();
        return reizen.gesorteerdOpDuur();
    }

    public int getAantal(){
       return reizen.getAantal();
    }
}
