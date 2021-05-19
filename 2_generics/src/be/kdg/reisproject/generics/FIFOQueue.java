package be.kdg.reisproject.generics;

/**
 * @author Lotte Vanhalst
 * @version 1.0 1/04/2019 16:36
 */
public interface FIFOQueue <T> {
    boolean enqueue(T element, int priority);
    T dequeue();
    int search(T element);
    int getSize();
}
