package be.kdg.reisproject.model;

import be.kdg.reisproject.reflection.CanRun;

/**
 * @author Lotte Vanhalst
 * @version 1.0 2/04/2019 17:44
 */
public class Land {
    private String naam;
    private Werelddeel werelddeel;
    private String taal;
    private String munteenheid;

    public String getNaam() {
        return naam;
    }

    @CanRun("België")
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

    @CanRun
    public void setWerelddeel(Werelddeel werelddeel) {
        if (werelddeel == null){
            throw new IllegalArgumentException("Gelieve een werelddeel in te vullen.");
        } else {
            this.werelddeel = werelddeel;
        }
    }

    public String getTaal() {
        return taal;
    }

    @CanRun("Nederlands")
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

    @CanRun("Euro")
    public void setMunteenheid(String munteenheid) {
        if (munteenheid == null){
            throw new IllegalArgumentException("Gelieve de munteenheid in te vullen.");
        } else {
            this.munteenheid = munteenheid;
        }
    }
}
