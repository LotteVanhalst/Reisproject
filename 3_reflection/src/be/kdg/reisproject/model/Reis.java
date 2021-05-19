package be.kdg.reisproject.model;

import be.kdg.reisproject.reflection.CanRun;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * @author Lotte Vanhalst
 * @version 1.0 1/04/2019 13:18
 */
public class Reis extends Land implements Comparable<Reis>{
    private LocalDate begindatum;
    private int aantalDagen;
    private double kostprijs;
    private double gemiddeldeTemp;
    private double zakgeld;

    public Reis(String naam, Werelddeel werelddeel, String begindatum, int aantalDagen, double kostprijs, String taal, String munteenheid, double gemiddeldeTemp, double zakgeld) {
        setNaam(naam);
        setWerelddeel(werelddeel);
        setBegindatum(begindatum);
        setAantalDagen(aantalDagen);
        setKostprijs(kostprijs);
        setTaal(taal);
        setMunteenheid(munteenheid);
        setGemiddeldeTemp(gemiddeldeTemp);
        setZakgeld(zakgeld);
    }

    public Reis(String naam, String begindatum, int aantalDagen, double kostprijs) {
        this(naam, Werelddeel.EUROPA, begindatum, aantalDagen, kostprijs, "Ongekend", "Ongekend", 0, 0);
    }

    public Reis(){
        this("Ongekend", Werelddeel.EUROPA, "31/12/2099", 7, 500, "Ongekend", "Ongekend", 0,0);
    }

    public LocalDate getBegindatum() {
        return begindatum;
    }

    @CanRun("31/12/2019")
    public void setBegindatum(String begindatum) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        LocalDate datum = LocalDate.parse(begindatum, formatter);
        if (datum.isBefore(LocalDate.now())){
            throw new IllegalArgumentException("Gelieve een datum in te vullen die later is dan vandaag.");
        } else {
            this.begindatum = datum;
        }
    }

    public int getAantalDagen() {
        return aantalDagen;
    }

    @CanRun
    public void setAantalDagen(int aantalDagen) {
        if (aantalDagen < 7 || aantalDagen > 30){
            throw new IllegalArgumentException("Gelieve het juiste aantal dagen in te vullen.");
        } else {
            this.aantalDagen = aantalDagen;
        }
    }

    public double getKostprijs() {
        return kostprijs;
    }

    @CanRun
    public void setKostprijs(double kostprijs) {
        if (kostprijs < (aantalDagen/7)*500 || kostprijs > (aantalDagen/7)*2500){
            throw new IllegalArgumentException("Gelieve de juiste kostprijs in te vullen.");
        } else {
            this.kostprijs = kostprijs;
        }
    }

    public double getGemiddeldeTemp() {
        return gemiddeldeTemp;
    }

    @CanRun
    public void setGemiddeldeTemp(double gemiddeldeTemp) {
        if (gemiddeldeTemp < -15 || gemiddeldeTemp > 40){
            throw new IllegalArgumentException("Gelieve de juiste gemiddelde temperatuur in te vullen.");
        } else {
            this.gemiddeldeTemp = gemiddeldeTemp;
        }
    }

    public double getZakgeld() {
        return zakgeld;
    }

    @CanRun
    public void setZakgeld(double zakgeld) {
        if (zakgeld < 0 || zakgeld > 700){
            throw new IllegalArgumentException("Gelieve het juiste zakgeld in te vullen.");
        } else {
            this.zakgeld = zakgeld;
        }
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
        int i = this.getNaam().compareTo(other.getNaam());
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

