package be.kdg.reisproject.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * @author Lotte Vanhalst
 * @version 1.0 1/04/2019 13:18
 */
public final class Reis implements Comparable<Reis>{
    private final String naam;
    private final Werelddeel werelddeel;
    private final LocalDate begindatum;
    private final int aantalDagen;
    private final double kostprijs;
    private final String taal;
    private final String munteenheid;
    private final double gemiddeldeTemp;
    private final double zakgeld;

    public Reis(String naam, Werelddeel werelddeel, LocalDate begindatum, int aantalDagen, double kostprijs, String taal, String munteenheid, double gemiddeldeTemp, double zakgeld) {
        this.naam = naam;
        this.werelddeel = werelddeel;
        this.begindatum = begindatum;
        this.aantalDagen = aantalDagen;
        this.kostprijs = kostprijs;
        this.taal = taal;
        this.munteenheid = munteenheid;
        this.gemiddeldeTemp = getGemiddeldeTemp();
        this.zakgeld = zakgeld;
    }

    public Reis(String naam, LocalDate begindatum, int aantalDagen, double kostprijs) {
        this(naam, Werelddeel.EUROPA, begindatum, aantalDagen, kostprijs, "Ongekend", "Ongekend", 0, 0);
    }

    public Reis(){
        this("Ongekend", Werelddeel.EUROPA, LocalDate.of(2019,12,31), 7, 500, "Ongekend", "Ongekend", 0,0);
    }

    public String getNaam() {
        return naam;
    }

    public Werelddeel getWerelddeel() {
        return werelddeel;
    }

    public LocalDate getBegindatum() {
        return begindatum;
    }

    public int getAantalDagen() {
        return aantalDagen;
    }

    public double getKostprijs() {
        return kostprijs;
    }

    public String getTaal() {
        return taal;
    }

    public String getMunteenheid() {
        return munteenheid;
    }

    public double getGemiddeldeTemp() {
        return gemiddeldeTemp;
    }


    public double getZakgeld() {
        return zakgeld;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reis reis = (Reis) o;
        return getAantalDagen() == reis.getAantalDagen() &&
                Objects.equals(getNaam(), reis.getNaam()) &&
                Objects.equals(getBegindatum(), reis.getBegindatum());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNaam(), getBegindatum(), getAantalDagen());
    }


    @Override
    public int compareTo(Reis other) {
        int i = this.naam.compareTo(other.naam);
        if (i != 0) {return i;}
        else{
            i = this.begindatum.compareTo(other.begindatum);
        }
        if (i != 0) {return i;}
        else{
            i = this.aantalDagen - other.aantalDagen;
            return i;
        }
    }

    @Override
    public String toString() {
        return String.format("Reis: %-20s Vertrek op: %-20s Aantal dagen: %-10d Totale kostprijs (zakgeld niet inbegrepen): %.2f euro",
                getNaam(), getBegindatum(),getAantalDagen(), getKostprijs());

    }
}

