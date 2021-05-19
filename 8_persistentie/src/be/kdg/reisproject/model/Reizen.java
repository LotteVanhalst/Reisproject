package be.kdg.reisproject.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @author Lotte Vanhalst
 * @version 1.0 1/04/2019 13:18
 */
public class Reizen implements Serializable, ReisDao {
    private TreeSet<Reis> beschikbareReizen;

    public Reizen() {
        beschikbareReizen = new TreeSet<>();
    }

    public TreeSet<Reis> getBeschikbareReizen() {
        return beschikbareReizen;
    }

    @Override
    public void voegToe(Reis reis){
        beschikbareReizen.add(reis);
    }

    @Override
    public void verwijder(String naam, LocalDate begindatum, int aantalDagen) {
        Reis verwijderReis = null;
        for (Reis reis : beschikbareReizen) {
            if (naam.equals(reis.getNaam()) && begindatum.equals(reis.getBegindatum()) && aantalDagen == reis.getAantalDagen()) {
                verwijderReis = reis;
            }
        }
        beschikbareReizen.remove(verwijderReis);
    }

    @Override
    public Reis zoek(String naam, LocalDate begindatum, int aantalDagen){
        Reis zoekReis = null;
        for (Reis reis : beschikbareReizen) {
            if (naam.equals(reis.getNaam()) && begindatum.equals(reis.getBegindatum()) && aantalDagen == reis.getAantalDagen()) {
                zoekReis = reis;
            }
        }
        return zoekReis;
    }

    @Override
    public List<Reis> gesorteerdOpKostprijs(){
        List<Reis> gesorteerdeReizen = new ArrayList<>(beschikbareReizen);
        Collections.sort(gesorteerdeReizen, new KostprijsComparator());
        return gesorteerdeReizen;
    }

    @Override
    public List<Reis> gesorteerdOpDatum(){
        List<Reis> gesorteerdeReizen = new ArrayList<>(beschikbareReizen);
        Collections.sort(gesorteerdeReizen, new DatumComparator());
        return gesorteerdeReizen;
    }

    @Override
    public List<Reis> gesorteerdOpDuur(){
        List<Reis> gesorteerdeReizen = new ArrayList<>(beschikbareReizen);
        Collections.sort(gesorteerdeReizen, new DuurComparator());
        return gesorteerdeReizen;
    }

    public int getAantal(){
        return beschikbareReizen.size();
    }

    public class KostprijsComparator implements Comparator<Reis> {
        @Override
        public int compare(Reis een, Reis twee){
            return (int)(een.getKostprijs() - twee.getKostprijs());
        }
    }

    public class DatumComparator implements Comparator<Reis>{
        @Override
        public int compare(Reis een, Reis twee){
            return een.getBegindatum().compareTo(twee.getBegindatum());
        }
    }

    public class DuurComparator implements Comparator<Reis>{
        @Override
        public int compare(Reis een, Reis twee){
            return een.getAantalDagen() - twee.getAantalDagen();
        }
    }

}
