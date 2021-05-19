package be.kdg.reisproject;

import be.kdg.reisproject.threading.ReisRunnable;

import java.time.LocalDate;
import java.util.function.Predicate;

/**
 * @author Lotte Vanhalst
 * @version 1.0 10/05/2019 17:04
 */
public class Demo_10 {
    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());
        int aantal = Runtime.getRuntime().availableProcessors();
        double tijd = 0.0;
        int testcount;
        for(testcount = 0; testcount<100; testcount++) {

            long start = System.currentTimeMillis();
            Thread[] threads = new Thread[4];
            ReisRunnable reis1 = new ReisRunnable(reis -> reis.getKostprijs() > 2000);
            ReisRunnable reis2 = new ReisRunnable(reis -> reis.getNaam().charAt(0) == 'A');
            ReisRunnable reis3 = new ReisRunnable(reis -> reis.getBegindatum().isAfter(LocalDate.of(2020, 1, 1)));
            ReisRunnable reis4 = new ReisRunnable(reis -> reis.getAantalDagen() < 15);
            threads[0] = new Thread(reis1);
            threads[1] = new Thread(reis2);
            threads[2] = new Thread(reis3);
            threads[3] = new Thread(reis4);

            threads[0].start();
            threads[1].start();
            threads[2].start();
            threads[3].start();

            for (Thread thread : threads) {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.getMessage();
                }
            }

            long end = System.currentTimeMillis();

            tijd += (end - start);

            System.out.println("\nReizen in de eerste lijst:");
            reis1.getList().stream().limit(5).forEach(System.out::println);
            System.out.println("\nReizen in de tweede lijst:");
            reis2.getList().stream().limit(5).forEach(System.out::println);
            System.out.println("\nReizen in de derde lijst:");
            reis3.getList().stream().limit(5).forEach(System.out::println);
            System.out.println("\nReizen in de vierde lijst:");
            reis4.getList().stream().limit(5).forEach(System.out::println);
        }
        double gemiddeldeTijd = tijd/100;

        System.out.printf("\n%s threads verzamelen elk 1000 reizen (gemiddele uit 100 runs): %fms", aantal, gemiddeldeTijd);
    }
}
