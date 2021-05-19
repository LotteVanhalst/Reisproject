package be.kdg.reisproject.threading;

import be.kdg.reisproject.model.Reis;
import be.kdg.reisproject.model.ReisFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Lotte Vanhalst
 * @version 1.0 10/05/2019 17:07
 */
public class ReisRunnable implements Runnable{
    private Predicate<Reis> voorwaarde;
    private List<Reis> reizen;

    public ReisRunnable (Predicate<Reis> voorwaarde)  {
        this.voorwaarde = voorwaarde;
        reizen = new ArrayList<>();
    }

    public void run(){
        Supplier<Reis> reis = () -> ReisFactory.newRandomReis();
        reizen = Stream.generate(reis).filter(voorwaarde).limit(1000).collect(Collectors.toList());
    }

    public List<Reis> getList() {
        return reizen;
    }
}