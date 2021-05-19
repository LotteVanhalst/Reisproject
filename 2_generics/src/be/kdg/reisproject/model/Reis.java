package be.kdg.reisproject.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Deze klasse is de basisklasse waarbij een reis kan aangemaakt worden.
 * @author Lotte Vanhalst
 * @version 1.0 1/04/2019 13:18
 * @see <a href="https://www.belgium.be/nl/familie/internationaal/reisdocumenten/visum">Visumaanvragen</a>
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

    /**
     * Dit is de constructor voor een reis.
     * @param naam De naam van het land
     * @param werelddeel In welk werelddeel het land ligt
     * @param begindatum Wanneer de reis begint
     * @param aantalDagen Hoeveel dagen de reis duurt
     * @param kostprijs Hoeveel de reis kost. Alles behalve het zakgeld is inbegrepen.
     * @param taal Welke taal er in het land gesproken wordt
     * @param munteenheid Wat de munteenheid van het land is
     * @param gemiddeldeTemp Wat de gemiddelde temperatuur is tijdens de periode dat de reis zal plaatsvinden
     * @param zakgeld Hoeveel je best kan voorzien voor eigen verbruik
     */
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

    /**
     * Dit is een constructor met enkel de belangrijkste attributen die absoluut nodig zijn om de reis te identifiëren.
     * De andere variabalen worden leeg, onbekend of 0 ingevuld.
     * @param naam De naam van het land
     * @param begindatum Wanneer de reis begint
     * @param aantalDagen Hoeveel dagen de reis duurt
     * @param kostprijs Hoeveel de reis kost.
     */
    public Reis(String naam, String begindatum, int aantalDagen, double kostprijs) {
        this(naam, Werelddeel.EUROPA, begindatum, aantalDagen, kostprijs, "Ongekend", "Ongekend", 0, 0);
    }

    /**
     * Dit is de default constructor waarbij alles leeg, onbekend of 0 wordt ingevuld.
     */
    public Reis(){
        this("Ongekend", Werelddeel.EUROPA, "31/12/2019", 7, 500, "Ongekend", "Ongekend", 0,0);
    }

    /**
     * Geeft de naam van het land waar de reis naar toe gaat terug
     * @return De naam van het land
     */
    public String getNaam() {
        return naam;
    }

    /**
     * Er wordt gecontroleerd of de naam van het land ingevuld is.
     * @param naam naam van het land
     * @throws IllegalArgumentException
     */
    public void setNaam(String naam) {
        if (naam == null){
            throw new IllegalArgumentException("Gelieve de naam in te vullen.");
        } else {
            this.naam = naam;
        }
    }

    /**
     * Geeft de naam van het werelddeel terug
     * @return werelddeel
     * @see Werelddeel Werelddeel
     */
    public Werelddeel getWerelddeel() {
        return werelddeel;
    }

    /**
     * Er wordt gecontroleerd of de naam van het werelddeel ingevuld is.
     * @param werelddeel De naam van het werelddeel
     * @throws IllegalArgumentException
     * @see Werelddeel Werelddeel
     */
    public void setWerelddeel(Werelddeel werelddeel) {
        if (werelddeel == null){
            throw new IllegalArgumentException("Gelieve een werelddeel in te vullen.");
        } else {
            this.werelddeel = werelddeel;
        }
    }

    /**
     * Geeft de startdatum van de reis terug
     * @return startdatum
     */
    public LocalDate getBegindatum() {
        return begindatum;
    }

    /**
     * Er wordt gecontroleerd of de datum in de toekomst ligt.
     * @param begindatum De startdatum van de reis
     * @throws IllegalArgumentException
     */
    public void setBegindatum(String begindatum) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        LocalDate datum = LocalDate.parse(begindatum, formatter);
        if (datum.isBefore(LocalDate.now())){
            throw new IllegalArgumentException("Gelieve een datum in te vullen die later is dan vandaag.");
        } else {
            this.begindatum = datum;
        }
    }

    /**
     * Geeft de startdatum van de reis terug
     * @return aantal dagen
     */
    public int getAantalDagen() {
        return aantalDagen;
    }

    /**
     * Er wordt gecontroleerd of de reis op zijn minst een week duurt en niet langer dan 30 dagen.
     * @param aantalDagen Het aantal dagen dat de reis duurt
     * @throws IllegalArgumentException
     */
    public void setAantalDagen(int aantalDagen) {
        if (aantalDagen < 7 || aantalDagen > 30){
            throw new IllegalArgumentException("Gelieve het juiste aantal dagen in te vullen.");
        } else {
            this.aantalDagen = aantalDagen;
        }
    }

    /**
     * Geeft de kostprijs van de reis terug
     * @return kostprijs
     */
    public double getKostprijs() {
        return kostprijs;
    }

    /**
     * Er wordt gecontroleerd of de reis niet goedkoper is dan 500 euro per week en niet duurder dan 2500 euro per week.
     * @param kostprijs De kostrpijs van de reis
     * @throws IllegalArgumentException
     */
    public void setKostprijs(double kostprijs) {
        if (kostprijs < (aantalDagen/7)*500 || kostprijs > (aantalDagen/7)*2500){
            throw new IllegalArgumentException("Gelieve de juiste kostprijs in te vullen.");
        } else {
            this.kostprijs = kostprijs;
        }
    }

    /**
     * Geeft de taal terug die gesproken wordt in het land. Dit kunnen ook meerdere talen zijn.
     * @return taal van het land
     */
    public String getTaal() {
        return taal;
    }

    /**
     * Er wordt gecontroleerd of de taal van het land ingevuld is.
     * @param taal De taal of talen die gesproken worden in het land
     * @throws IllegalArgumentException
     */
    public void setTaal(String taal) {
        if (taal == null){
            throw new IllegalArgumentException("Gelieve de taal in te vullen.");
        } else {
            this.taal = taal;
        }
    }

    /**
     * Geeft de munteenheid van het land terug
     * @return munteenheid van het land
     */
    public String getMunteenheid() {
        return munteenheid;
    }

    /**
     * Er wordt gecontroleerd of de munteenheid van het land ingevuld is.
     * @param munteenheid De munteenheid van het land
     * @throws IllegalArgumentException
     */
    public void setMunteenheid(String munteenheid) {
        if (munteenheid == null){
            throw new IllegalArgumentException("Gelieve de munteenheid in te vullen.");
        } else {
            this.munteenheid = munteenheid;
        }
    }

    /**
     * Geeft de gemiddelde temperatuur terug van de periode waarin de reis doorgaat
     * @return gemiddelde temperatuur
     */
    public double getGemiddeldeTemp() {
        return gemiddeldeTemp;
    }

    /**
     * Er wordt gecontroleerd of de gemiddelde temperatuur niet kouder is dan -15° en niet warmer dan 40°.
     * @param gemiddeldeTemp De gemiddelde temperatuur van de periode waarin de reis doorgaat
     * @throws IllegalArgumentException
     */
    public void setGemiddeldeTemp(double gemiddeldeTemp) {
        if (gemiddeldeTemp < -15 || gemiddeldeTemp > 40){
            throw new IllegalArgumentException("Gelieve de juiste gemiddelde temperatuur in te vullen.");
        } else {
            this.gemiddeldeTemp = gemiddeldeTemp;
        }
    }

    /**
     * Geeft het bedrag terug dat je ongeveer moet voorzien voor eigen verbruik.
     * @return zakgeld
     */
    public double getZakgeld() {
        return zakgeld;
    }

    /**
     * Er wordt gecontroleerd of het bedrag van het zakgeld niet negatief is en niet hoger is dan 700 euro.
     * @param zakgeld Het bedrag dat je moet voorzien voor eigen verbruik
     * @throws IllegalArgumentException
     */
    public void setZakgeld(double zakgeld) {
        if (zakgeld < 0 || zakgeld > 700){
            throw new IllegalArgumentException("Gelieve het juiste zakgeld in te vullen.");
        } else {
            this.zakgeld = zakgeld;
        }
    }

    /**
     * Deze methode wordt gebruikt om na te gaan of er niet al eenzelfde reis bestaat. Er wordt gecontroleerd op
     * land, vertrekdatum en aantal dagen.
     * @param o met deze reis wordt er vergeleken
     * @return true als de reizen gelijk zijn en false als de reizen niet gelijk zijn
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reis reis = (Reis) o;
        return getAantalDagen() == reis.getAantalDagen() &&
                Objects.equals(getNaam(), reis.getNaam()) &&
                Objects.equals(getBegindatum(), reis.getBegindatum());
    }

    /**
     * Er wordt ook gekeken naar de hashcode om na te gaan of twee reizen gelijk zijn. De hashcode geeft het fysieke adres terug.
     * @return De hashcode van de reis waarbij de codes van de naam, de vertrekdatum en het aantal dagen bekeken worden.
     */
    @Override
    public int hashCode() {
        return Objects.hash(getNaam(), getBegindatum(), getAantalDagen());
    }

    /**
     * De reizen worden gesorteerd volgens de naam van het land, vervolgens op vertrekdatum en als laatste nog op het aantal dagen dat de reis duurt.
     * @param other Deze reis wordt gesorteerd met de reizen in de TreeSet
     * @return Het verschil in de twee reizen. Indien deze negatief is, staat de reis in de TreeSet op de eerste plaats.
     * Is deze positief staat de andere reis op de eerste plaats. Bij 0 zijn de reizen gelijkwaardig qua sortering.
     */
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

    /**
     * De belangrijkste variabalen worden vermeld als de reis afgedrukt wordt: de naam, de vertrekdatum, het aantal dagen en de kostprijs.
     * @return Geeft de reis terug met de belangrijkste variabelen.
     */
    @Override
    public String toString() {
            return String.format("Reis: %-20s Vertrek op: %-20s Aantal dagen: %-10d Totale kostprijs (zakgeld niet inbegrepen): %.2f euro",
                    getNaam(), getBegindatum(),getAantalDagen(), getKostprijs());

        }
}

