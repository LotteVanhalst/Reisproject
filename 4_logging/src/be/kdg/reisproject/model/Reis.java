package be.kdg.reisproject.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Lotte Vanhalst
 * @version 1.0 1/04/2019 13:18
 */
public class Reis implements Comparable<Reis>{
    private String naam;
    private Werelddeel werelddeel;
    private LocalDate begindatum;
    private int aantalDagen;
    private double kostprijs;
    private String taal;
    private String munteenheid;
    private double gemiddeldeTemp;
    private double zakgeld;
    private Logger logger = Logger.getLogger("be.kdg.model.Reis");

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
        this("Ongekend", Werelddeel.EUROPA, "31/12/2019", 7, 500, "Ongekend", "Ongekend", 0,0);
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        if (naam == null){
            logger.log(Level.SEVERE,"Gelieve de naam in te vullen.");
        } else {
            this.naam = naam;
        }
    }

    public Werelddeel getWerelddeel() {
        return werelddeel;
    }

    public void setWerelddeel(Werelddeel werelddeel) {
        if (werelddeel == null){
            logger.log(Level.SEVERE,werelddeel + " is geen correct werelddeel. Gelieve een correct werelddeel" +
                    "in te vullen voor " + this.getNaam());
        } else {
            this.werelddeel = werelddeel;
        }
    }

    public LocalDate getBegindatum() {
        return begindatum;
    }

    public void setBegindatum(String begindatum) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        LocalDate datum = LocalDate.parse(begindatum, formatter);
        if (datum.isBefore(LocalDate.now())){
            logger.log(Level.SEVERE, "De datum " + begindatum + " ligt in het verleden. " +
                    "Gelieve een datum in te vullen voor de reis naar " + this.getNaam() + " die later is dan vandaag.");
        } else {
            this.begindatum = datum;
        }
    }

    public int getAantalDagen() {
        return aantalDagen;
    }

    public void setAantalDagen(int aantalDagen) {
        if (aantalDagen < 7 || aantalDagen > 30){
            logger.log(Level.SEVERE, "Het aantal dagen " + aantalDagen + " is niet correct. " +
                    "Gelieve het juiste aantal dagen in te vullen voor de reis naar " + this.getNaam() + ".");
        } else {
            this.aantalDagen = aantalDagen;
        }
    }

    public double getKostprijs() {
        return kostprijs;
    }

    public void setKostprijs(double kostprijs) {
        if (kostprijs < (aantalDagen/7)*500 || kostprijs > (aantalDagen/7)*2500){
            logger.log(Level.SEVERE, "De kostprijs " + kostprijs + " euro is niet correct. " +
                    "Gelieve de juiste kostprijs in te vullen voor de reis naar " + this.getNaam() + ".");
        } else {
            this.kostprijs = kostprijs;
        }
    }

    public String getTaal() {
        return taal;
    }

    public void setTaal(String taal) {
        if (taal == null){
            logger.log(Level.SEVERE, "De taal kan niet " + taal + " zijn. " +
                    "Gelieve de taal in te vullen voor de reis naar " + this.getNaam() + ".");
        } else {
            this.taal = taal;
        }
    }

    public String getMunteenheid() {
        return munteenheid;
    }

    public void setMunteenheid(String munteenheid) {
        if (munteenheid == null){
            logger.log(Level.SEVERE, "De munteenheid kan niet " + munteenheid + " zijn. " +
                    "Gelieve de munteenheid in te vullen voor de reis naar " + this.getNaam() + ".");
        } else {
            this.munteenheid = munteenheid;
        }
    }

    public double getGemiddeldeTemp() {
        return gemiddeldeTemp;
    }

    public void setGemiddeldeTemp(double gemiddeldeTemp) {
        if (gemiddeldeTemp < -15 || gemiddeldeTemp > 40){
            logger.log(Level.SEVERE, "De gemiddelde temperatuur " + gemiddeldeTemp + " is niet correct. " +
                    "Gelieve de juiste gemiddelde temperatuur in te vullen voor de reis naar " + this.getNaam() + ".");
        } else {
            this.gemiddeldeTemp = gemiddeldeTemp;
        }
    }

    public double getZakgeld() {
        return zakgeld;
    }

    public void setZakgeld(double zakgeld) {
        if (zakgeld < 0 || zakgeld > 700){
            logger.log(Level.SEVERE, "Het bedrag van " + zakgeld + " euro is niet correct. " +
                    "Gelieve het juiste zakgeld in te vullen voor de reis naar " + this.getNaam() + ".");
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

