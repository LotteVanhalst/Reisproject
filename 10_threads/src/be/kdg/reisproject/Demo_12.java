package be.kdg.reisproject;

import be.kdg.reisproject.model.Reis;
import be.kdg.reisproject.threading.ReisCallable;
import be.kdg.reisproject.threading.ReisRunnable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author Lotte Vanhalst
 * @version 1.0 10/05/2019 18:55
 */
public class Demo_12 {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(3);
        List<Future<List<Reis>>> list = new ArrayList<>();
        double tijd = 0.0;
        int testcount;
        for(testcount = 0; testcount<100; testcount++) {

            long start = System.currentTimeMillis();
            ReisCallable reis1 = new ReisCallable(reis -> reis.getKostprijs() > 2000);
            ReisCallable reis2 = new ReisCallable(reis -> reis.getNaam().charAt(0) == 'A');
            ReisCallable reis3 = new ReisCallable(reis -> reis.getAantalDagen() < 15);
            Future<List<Reis>> future1 = pool.submit(reis1);
            list.add(future1);
            Future<List<Reis>> future2 = pool.submit(reis2);
            list.add(future2);
            Future<List<Reis>> future3 = pool.submit(reis3);
            list.add(future3);


            long end = System.currentTimeMillis();

            tijd += (end - start);
            try {
                System.out.println("\nReizen in de eerste lijst:");
                future1.get().stream().limit(5).forEach(System.out::println);
                System.out.println("\nReizen in de tweede lijst:");
                future2.get().stream().limit(5).forEach(System.out::println);
                System.out.println("\nReizen in de derde lijst:");
                future3.get().stream().limit(5).forEach(System.out::println);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        pool.shutdown();
        double gemiddeldeTijd = tijd/100;

        System.out.printf("\n3 Futures verzamelen elk 1000 reizen (gemiddele uit 100 runs): %fms", gemiddeldeTijd);
    }
}
