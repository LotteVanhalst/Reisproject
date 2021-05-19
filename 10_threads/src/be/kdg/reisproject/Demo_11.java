package be.kdg.reisproject;

import be.kdg.reisproject.model.Reis;
import be.kdg.reisproject.model.ReisFactory;
import be.kdg.reisproject.threading.ReisAttacker;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Lotte Vanhalst
 * @version 1.0 10/05/2019 18:26
 */
public class Demo_11 {
    public static void main(String[] args) {
        List<Reis> reizen = new ArrayList<>();
        Supplier<Reis> randomReis = () -> ReisFactory.newRandomReis();
        reizen = Stream.generate(randomReis).limit(1000).collect(Collectors.toList());

        System.out.println("Voor uitzuivering: ");
        System.out.println("Aantal reizen duurder dan 2000 euro: " + reizen.stream().filter(reis -> reis.getKostprijs() > 2000).count());
        System.out.println("Aantal reizen die beginnen met een A: " + reizen.stream().filter(reis -> reis.getNaam().charAt(0) == 'A').count());
        System.out.println("Aantal reizen korter dan 15 dagen: " + reizen.stream().filter(reis -> reis.getAantalDagen() < 15).count());

        Thread[] threads = new Thread[3];

        ReisAttacker attacker1 = new ReisAttacker(reizen, reis -> reis.getKostprijs() > 2000);
        ReisAttacker attacker2 = new ReisAttacker(reizen, reis -> reis.getNaam().charAt(0) == 'A');
        ReisAttacker attacker3 = new ReisAttacker(reizen, reis -> reis.getAantalDagen() < 15);

        threads[0] = new Thread(attacker1);
        threads[1] = new Thread(attacker2);
        threads[2] = new Thread(attacker3);

        attacker1.run();
        attacker2.run();
        attacker3.run();

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.getMessage();
            }
        }

        System.out.println();

        System.out.println("Na uitzuivering: ");
        System.out.println("Aantal reizen duurder dan 2000 euro: " + attacker1.getList().stream().filter(reis -> reis.getKostprijs() > 2000).count());
        System.out.println("Aantal reizen die beginnen met een A: " + attacker2.getList().stream().filter(reis -> reis.getNaam().charAt(0) == 'A').count());
        System.out.println("Aantal reizen korter dan 15 dagen: " + attacker3.getList().stream().filter(reis -> reis.getAantalDagen() < 15).count());
    }
}
