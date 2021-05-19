package be.kdg.reisproject;


import be.kdg.reisproject.model.Reis;
import be.kdg.reisproject.model.Reizen;
import be.kdg.reisproject.model.Land;
import be.kdg.reisproject.reflection.ReflectionTools;

import java.lang.reflect.InvocationTargetException;
import java.util.Random;

/**
 * @author Lotte Vanhalst
 * @version 1.0 1/04/2019 15:45
 */
public class Demo_3 {

    public static void main(String[] args) {
        ReflectionTools.classAnalysis(Land.class);
        ReflectionTools.classAnalysis(Reis.class);
        ReflectionTools.classAnalysis(Reizen.class);

        try {
            ReflectionTools.runAnnotated(Reis.class);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.getMessage();
        }
    }
}
