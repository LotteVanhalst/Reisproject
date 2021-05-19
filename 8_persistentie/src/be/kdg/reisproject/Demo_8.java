package be.kdg.reisproject;

import be.kdg.reisproject.data.Data;
import be.kdg.reisproject.model.Reis;
import be.kdg.reisproject.model.Reizen;
import be.kdg.reisproject.model.Werelddeel;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Lotte Vanhalst
 * @version 1.0 1/05/2019 17:59
 */
public class Demo_8 {
    public static void main(String[] args) {
        Data lijst = new Data();
        Reizen reizen = new Reizen();
        Data.geefReizen().forEach(reizen::voegToe);

        List<Reis> lijstReizen = new ArrayList<>(reizen.getBeschikbareReizen());

        System.out.println();
        System.out.println("Voor serialize:");
        lijstReizen.forEach(System.out::println);
        System.out.println(lijstReizen.get(2).getGemiddeldeTemp());

        try(FileOutputStream fileOut = new FileOutputStream("8_persistentie/db/reizen.ser");
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(lijstReizen);
            lijstReizen.clear();
            } catch (IOException exc){
            exc.printStackTrace();
        }

        try (FileInputStream fileIn = new FileInputStream("8_persistentie/db/reizen.ser");
             ObjectInputStream in = new ObjectInputStream(fileIn)){
            System.out.println();
            System.out.println("Na deserialize:");
            lijstReizen = (ArrayList)in.readObject();
            lijstReizen.forEach(System.out::println);
            System.out.println(lijstReizen.get(2).getGemiddeldeTemp());
        } catch (Exception exc){
            exc.printStackTrace();
        }
    }
}
