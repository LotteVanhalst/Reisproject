package be.kdg.reisproject.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * @author Lotte Vanhalst
 * @version 1.0 1/04/2019 13:18
 */
public class Reis implements Comparable<Reis>, Serializable {
    private String naam;
    private transient Werelddeel werelddeel;
    private LocalDate begindatum;
    private int aantalDagen;
    private double kostprijs;
    private transient String taal;
    private transient String munteenheid;
    private transient double gemiddeldeTemp;
    private double zakgeld;
    private static final long serialVersionUID = 1L;
    private int id;

    public Reis(String naam, Werelddeel werelddeel, LocalDate begindatum, int aantalDagen, double kostprijs, String taal, String munteenheid, double gemiddeldeTemp, double zakgeld, int id) {
        setNaam(naam);
        setWerelddeel(werelddeel);
        setBegindatum(begindatum);
        setAantalDagen(aantalDagen);
        setKostprijs(kostprijs);
        setTaal(taal);
        setMunteenheid(munteenheid);
        setGemiddeldeTemp(gemiddeldeTemp);
        setZakgeld(zakgeld);
        setId(id);
    }

    public Reis(String naam, Werelddeel werelddeel, LocalDate begindatum, int aantalDagen, double kostprijs, String taal, String munteenheid, double gemiddeldeTemp, double zakgeld) {
        this(naam, werelddeel, begindatum, aantalDagen, kostprijs, taal, munteenheid, gemiddeldeTemp, zakgeld, -1);
    }

    public Reis(String naam, LocalDate begindatum, int aantalDagen, double kostprijs) {
        this(naam, Werelddeel.EUROPA, begindatum, aantalDagen, kostprijs, "Ongekend", "Ongekend", 0, 0, -1);
    }

    public Reis(){
        this("Ongekend", Werelddeel.EUROPA, LocalDate.of(2019, 12, 31), 7, 500, "Ongekend", "Ongekend", 0,0, -1);
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        if (naam == null){
            throw new IllegalArgumentException("Gelieve de naam in te vullen.");
        } else {
            this.naam = naam;
        }
    }

    public Werelddeel getWerelddeel() {
        return werelddeel;
    }

    public void setWerelddeel(Werelddeel werelddeel) {
        if (werelddeel == null){
            throw new IllegalArgumentException("Gelieve een werelddeel in te vullen.");
        } else {
            this.werelddeel = werelddeel;
        }
    }

    public LocalDate getBegindatum() {
        return begindatum;
    }

    public void setBegindatum(LocalDate begindatum) {
        if (begindatum.isBefore(LocalDate.now())){
            throw new IllegalArgumentException("Gelieve een datum in te vullen die later is dan vandaag.");
        } else {
            this.begindatum = begindatum;
        }
    }

    public int getAantalDagen() {
        return aantalDagen;
    }

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

    public void setKostprijs(double kostprijs) {
        if (kostprijs < (aantalDagen/7)*500 || kostprijs > (aantalDagen/7)*2500){
            throw new IllegalArgumentException("Gelieve de juiste kostprijs in te vullen.");
        } else {
            this.kostprijs = kostprijs;
        }
    }

    public String getTaal() {
        return taal;
    }

    public void setTaal(String taal) {
        if (taal == null){
            throw new IllegalArgumentException("Gelieve de taal in te vullen.");
        } else {
            this.taal = taal;
        }
    }

    public String getMunteenheid() {
        return munteenheid;
    }

    public void setMunteenheid(String munteenheid) {
        if (munteenheid == null){
            throw new IllegalArgumentException("Gelieve de munteenheid in te vullen.");
        } else {
            this.munteenheid = munteenheid;
        }
    }

    public double getGemiddeldeTemp() {
        return gemiddeldeTemp;
    }

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

    public void setZakgeld(double zakgeld) {
        if (zakgeld < 0 || zakgeld > 700){
            throw new IllegalArgumentException("Gelieve het juiste zakgeld in te vullen.");
        } else {
            this.zakgeld = zakgeld;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

