package be.kdg.reisproject.generics;

import be.kdg.reisproject.model.Reis;

import java.security.Key;
import java.util.*;

/**
 * Deze klasse zorgt voor een sortering van generieke elementen volgens prioriteit. Aangezien er meerdere elementen per prioriteit
 * kunnen zijn, is de value een lijst met alle elementen die bij de desbetreffende prioriteit horen.
 * @author Lotte Vanhalst
 * @version 1.0 1/04/2019 16:39
 */
public class PriorityQueue<T> implements FIFOQueue<T>{
    private TreeMap<Integer, List<T>> prioriteiten;

    /**
     * Dit is de constructor van de klasse. De TreeMap wordt geïnstantieerd.
     */
    public PriorityQueue() {
        prioriteiten = new TreeMap<>(Collections.reverseOrder());
    }

    /**
     * Deze methode wordt gebruikt om na te gaan of er niet al eenzelfde element bestaat.
     * @param o met dit element wordt er vergeleken
     * @return true als de elementen gelijk zijn en false als de elementen niet gelijk zijn
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PriorityQueue<?> that = (PriorityQueue<?>) o;
        return Objects.equals(prioriteiten, that.prioriteiten);
    }


    /**
     * Er wordt ook gekeken naar de hashcode om na te gaan of twee elementen gelijk zijn.
     * @return De hashcode van het element.
     */
    @Override
    public int hashCode() {
    return Objects.hash(prioriteiten);
}

    /**
     * Met deze methode wordt een element toegevoegd die nog niet bestaat. Het element wordt achteraan de lijst bij de desbetreffende
     * prioriteit geplaatst.
     * @param element Dit is generiek, de value van de TreeMap
     * @param priority De prioriteit die aan het element gegeven wordt.
     * @return Geeft true als het element nog niet bestond en dus toegevoegd werd. Geeft false als het element als bestond en dus niet meer toegevoegd werd.
     */
    @Override
    public boolean enqueue (T element, int priority){
        boolean gelukt = false;
        if (prioriteiten.keySet().contains(priority)) {
            if (!prioriteiten.get(priority).contains(element)) {
                prioriteiten.get(priority).add(element);
                gelukt = true;
            }
        } else {
            List<T> nieuwElement = new ArrayList<>();
            nieuwElement.add(element);
            prioriteiten.put(priority, nieuwElement);
        }
        return gelukt;
    }

    /**
     * Haalt het eerste element weg uit de TreeMap. Indien dit ervoor zorgt dat de lijst bij de debetreffende prioriteit leeg is,
     * wordt de prioriteit volledig weggehaald.
     * @return Het element dat weggehaald werd.
     */
    @Override
    public T dequeue() {
        T element = null;
        if (prioriteiten.size()>0) {
            element = prioriteiten.get(prioriteiten.firstKey()).remove(0);
            if (prioriteiten.get((prioriteiten.firstKey())).size() == 0) {
                prioriteiten.remove(prioriteiten.firstKey());
            }
        }
       return element;
    }

    /**
     * Kijkt na of het element in de TreeMap zit en geeft de positie van het element terug als dat zo is, rekening houdend met de lengte
     * van de lijst bij elke prioriteit.
     * @param element Dit element wordt opgezocht.
     * @return De positie van het element.
     */
    @Override
    public int search (T element) {
        int prior = -1;
        int teller = 0;
        for (Integer key : prioriteiten.keySet()) {
            if (prioriteiten.get(key).contains(element)) {
                teller += prioriteiten.get(key).indexOf(element) + 1;
                prior = teller;
            } else {
                teller += prioriteiten.get(key).size();
            }
        }
        return prior;
    }

    /**
     * Geeft de grootte van de TreeMap terug, rekening houdend met de lengte van de lijst bij elke prioriteit.
     * @return grootte van de TreeMap
     */
    @Override
    public int getSize(){
        int grootte = 0;
        for (Integer key : prioriteiten.keySet()) {
            grootte += prioriteiten.get(key).size();
        }
        return grootte;
    }

    /**
     * Geeft de TreeMap op een gestructureerde manier terug, één lijn per element.
     * @return TreeMap
     */
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (Integer key : prioriteiten.keySet()) {
            for (int i=0; i < prioriteiten.get(key).size(); i++){
                System.out.println(String.format("%d: %s", key, prioriteiten.get(key).get(i)));
            }
        }
        return output.toString();
    }
}
