package be.kdg.reisproject.patterns;

import be.kdg.reisproject.model.Reizen;

import java.util.Observable;
import java.util.Observer;

/**
 * @author Lotte Vanhalst
 * @version 1.0 10/04/2019 0:44
 */
public class ReizenObserver implements Observer {
    private ObservableReizen observableReizen;

    public ReizenObserver(ObservableReizen observableReizen){
        this.observableReizen = observableReizen;
    }

    public void update(Observable observableReizen, Object object){
        System.out.println("Observer meldt: " + object);
    }
}
