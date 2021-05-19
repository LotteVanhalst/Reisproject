package be.kdg.reisproject.threading;

import be.kdg.reisproject.model.Reis;

import java.util.List;
import java.util.function.Predicate;

/**
 * @author Lotte Vanhalst
 * @version 1.0 10/05/2019 18:20
 */
public class ReisAttacker implements Runnable{
    private static final Object LOCK = new Object();
    private Predicate<Reis> voorwaarde;
    private List<Reis> reizen;

    public ReisAttacker(List<Reis> reizen, Predicate<Reis> voorwaarde){
        this.reizen = reizen;
        this.voorwaarde = voorwaarde;
    }

    public void run(){
        synchronized (LOCK) {
            reizen.removeIf(voorwaarde);
        }
    }

    public List<Reis> getList() {
        return reizen;
    }
}
