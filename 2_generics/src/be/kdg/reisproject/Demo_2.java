package be.kdg.reisproject;


import be.kdg.reisproject.data.Data;
import be.kdg.reisproject.generics.PriorityQueue;
import be.kdg.reisproject.model.Reis;
import be.kdg.reisproject.model.Werelddeel;

import java.util.Random;

/**
 * @author Lotte Vanhalst
 * @version 1.0 1/04/2019 15:45
 */
public class Demo_2 {

    public static void main(String[] args) {
        PriorityQueue<String> myQueue = new PriorityQueue<>();
        myQueue.enqueue("alfa", 2);
        myQueue.enqueue("beta", 5);
        myQueue.enqueue("gamma", 2);
        myQueue.enqueue("delta", 3);
        System.out.println("Overzicht van de PriorityQueue:");
        System.out.println(myQueue.toString());
        System.out.println("aantal: " + myQueue.getSize());
        System.out.println("positie van gamma: " + myQueue.search("gamma"));
        for (int i = 0; i < 4; i++) {
            System.out.println("Dequeue: " + myQueue.dequeue());
        }
        System.out.println("Size na dequeue: " + myQueue.getSize());

        System.out.println();

        PriorityQueue<Reis> reizen = new PriorityQueue<>();
        Data lijst = new Data();
        Random random = new Random();
        for (Reis reis : Data.geefReizen()) {
            reizen.enqueue(reis, random.nextInt(5) + 1);
        }
        System.out.println("Overzicht van de reizen:");
        System.out.println(reizen.toString());
        System.out.println("Aantal: " + reizen.getSize());
        System.out.println("Positie van Vietnam: " + reizen.search(new Reis("Vietnam", Werelddeel.AZIE, "15/03/2020", 16, 1876.87, "Vietnamees", "Dong", 23, 250)));
        for (int i = 0; i < 3; i++) {
            System.out.println("Reizen: " + reizen.dequeue());
        }
        System.out.println("Size na dequeue: " + reizen.getSize());
    }
}
