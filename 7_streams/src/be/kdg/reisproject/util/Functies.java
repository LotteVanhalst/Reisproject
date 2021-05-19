package be.kdg.reisproject.util;

import be.kdg.reisproject.model.Reis;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @author Lotte Vanhalst
 * @version 1.0 15/04/2019 21:50
 */
public class Functies {
    public static <T> List<T> filteredList(List<T> reisList, Predicate<T> predicate){
       return reisList.stream().filter(a->predicate.test(a)).collect(Collectors.toList());
    }

    public static <T> Double average (List<T> reisList, ToDoubleFunction<T> mapper){
        return reisList.stream().mapToDouble(mapper).average().getAsDouble();
    }

    public static <T> long countIf(List<T> reisList, Predicate<T> predicate){
        return reisList.stream().filter(a->predicate.test(a)).count();
    }
}
