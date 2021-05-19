package be.kdg.reisproject.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @author Lotte Vanhalst
 * @version 1.0 1/04/2019 13:18
 */
@XmlRootElement (name = "reizen")
public class Reizen {
    private TreeSet<Reis> beschikbareReizen;
    private List<Reis> reisList = new ArrayList<>();

    public Reizen() {
        beschikbareReizen = new TreeSet<>();
    }

    public TreeSet<Reis> getBeschikbareReizen() {
        return beschikbareReizen;
    }

    public List<Reis> getReisList() {
        reisList = new ArrayList<>(beschikbareReizen);
        return reisList;
    }

    @XmlElement(name = "reis")
    public void setReisList(List<Reis> reisList) {
        this.reisList = reisList;
    }

    public void add(Reis reis){
        this.reisList.add(reis);
    }

    public void voegToe (Reis reis){
        beschikbareReizen.add(reis);
    }

    public void verwijder (String naam, String begindatum, int aantalDagen) {
        Reis verwijderReis = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        LocalDate datum = LocalDate.parse(begindatum, formatter);
        for (Reis reis : beschikbareReizen) {
            if (naam.equals(reis.getNaam()) && datum.equals(reis.getBegindatum()) && aantalDagen == reis.getAantalDagen()) {
                verwijderReis = reis;
            }
        }
        beschikbareReizen.remove(verwijderReis);
    }

    public Reis zoek(String naam, String begindatum, int aantalDagen){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        LocalDate datum = LocalDate.parse(begindatum, formatter);
        Reis zoekReis = null;
        for (Reis reis : beschikbareReizen) {
            if (naam.equals(reis.getNaam()) && datum.equals(reis.getBegindatum()) && aantalDagen == reis.getAantalDagen()) {
                zoekReis = reis;
            }
        }
        return zoekReis;
    }

    public List<Reis> gesorteerdOpKostprijs(){
        List<Reis> gesorteerdeReizen = new ArrayList<>(beschikbareReizen);
        Collections.sort(gesorteerdeReizen, new KostprijsComparator());
        return gesorteerdeReizen;
    }

    public List<Reis> gesorteerdOpDatum(){
        List<Reis> gesorteerdeReizen = new ArrayList<>(beschikbareReizen);
        Collections.sort(gesorteerdeReizen, new DatumComparator());
        return gesorteerdeReizen;
    }

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
