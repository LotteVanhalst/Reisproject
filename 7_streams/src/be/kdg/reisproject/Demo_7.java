package be.kdg.reisproject;


import be.kdg.reisproject.data.Data;
import be.kdg.reisproject.model.Reis;
import be.kdg.reisproject.model.Reizen;
import be.kdg.reisproject.util.Functies;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Collections.reverseOrder;

/**
 * @author Lotte Vanhalst
 * @version 1.0 1/04/2019 15:45
 */
public class Demo_7 {

    public static void main(String[] args) {
        Data lijst = new Data();
        Reizen reizen = new Reizen();
        Data.geefReizen().forEach(reizen::voegToe);

        List<Reis> gesorteerdNaam = new ArrayList<>(reizen.gesorteerdOp(Reis::getNaam));
        System.out.println("Reizen gesorteerd op naam:");
        gesorteerdNaam.forEach(System.out::println);

        List<Reis> gesorteerdDuur = new ArrayList<>(reizen.gesorteerdOp(Reis::getAantalDagen));
        System.out.println("\nReizen gesorteerd op duur:");
        gesorteerdDuur.forEach(System.out::println);

        List<Reis> gesorteerdTemp = new ArrayList<>(reizen.gesorteerdOp(Reis::getGemiddeldeTemp));
        System.out.println("\nReizen gesorteerd op gemiddelde temperatuur:");
        gesorteerdTemp.forEach(System.out::println);

        List<Reis> reizenFilter = new ArrayList<>(reizen.getBeschikbareReizen());
        reizenFilter = Functies.filteredList(reizenFilter, reis -> reis.getKostprijs()>2000);
        reizenFilter = Functies.filteredList(reizenFilter, reis -> reis.getGemiddeldeTemp()>25);
        reizenFilter = Functies.filteredList(reizenFilter, reis -> reis.getAantalDagen()>20);
        System.out.println("\nReizen duurder dan 2000 euro, warmer dan 25°C en langer dan 20 dagen:");
        reizenFilter.forEach(System.out::println);

        System.out.println();

        List<Reis> reizenAverage = new ArrayList<>(reizen.getBeschikbareReizen());
        System.out.printf("Gemiddelde kostprijs: %.2f euro\n", Functies.average(reizenAverage, Reis::getKostprijs));
        System.out.printf("Gemiddelde temperatuur: %.2f euro\n", Functies.average(reizenAverage, Reis::getGemiddeldeTemp));
        System.out.printf("Gemiddeld zakgeld: %.2f euro\n", Functies.average(reizenAverage, Reis::getZakgeld));
        System.out.printf("Gemiddeld aantal dagen: %.2f euro\n", Functies.average(reizenAverage, Reis::getAantalDagen));

        System.out.println();

        List<Reis> reizenCount = new ArrayList<>(reizen.getBeschikbareReizen());
        System.out.printf("Aantal reizen met een gemiddelde temperatuur lager dan 15°C: %d\n",
                Functies.countIf(reizenCount, reis -> reis.getGemiddeldeTemp()<15));

        System.out.printf("Aantal reizen met een duur langer dan 20 dagen: %d\n",
                Functies.countIf(reizenCount, reis -> reis.getAantalDagen()>20));

        System.out.printf("Aantal reizen met een kostprijs lager dan 1500 euro: %d\n",
                Functies.countIf(reizenCount, reis -> reis.getKostprijs()<1500));

        System.out.printf("Aantal reizen die na 2019 plaatsvinden: %d\n",
                Functies.countIf(reizenCount, reis -> reis.getBegindatum().getYear()>2019));

        System.out.println();

        List<Reis> reizenStream = new ArrayList<>(reizen.getBeschikbareReizen());

        long aantalReizen = reizenStream.stream().filter(a -> a.getKostprijs() > 2000).count();
        System.out.println("Aantal reizen duurder dan 2000 euro: " + aantalReizen);

        System.out.println();

        List<Reis> streamGesorteerd = reizenStream.stream().sorted((a,b) -> (a.getWerelddeel().compareTo(b.getWerelddeel())))
                .sorted((a,b) -> (a.getAantalDagen() - b.getAantalDagen())).collect(Collectors.toList());
        System.out.println("Alle reizen gesorteerd op wereddeel en dan op duur: ");
        streamGesorteerd.forEach(System.out::println);

        System.out.println();

        Stream<String> streamString = reizenStream.stream().map(a -> a.getTaal().toUpperCase())
                .distinct().sorted(reverseOrder());
        System.out.println("Alle talen in hoofdletters, omgekeerd gesorteerd en zonder dubbels: ");
        streamString.forEach(a -> System.out.print(a + ", "));

        System.out.println();
        System.out.println();

        Optional<Reis> willekeurigeReis = reizenStream.stream().filter(a -> a.getKostprijs() < 2000).findAny();
        System.out.println("Een willekeurige reis die minder dan 2000 euro kost: ");
        System.out.println(willekeurigeReis.get().getNaam() + ": " + willekeurigeReis.get().getKostprijs() + " euro");

        System.out.println();

        Reis langsteReis = reizenStream.stream().max((a,b) -> a.getAantalDagen() - b.getAantalDagen()).get();
        System.out.println("De langste reis: " + langsteReis.getNaam());

        System.out.println();

        List<String> lijstReis = reizenStream.stream().filter(a -> a.getBegindatum().getYear() > 2019)
                .map(Reis::getNaam).sorted().collect(Collectors.toList());

        System.out.println("Lijst met gesorteerde landen van reizen in 2020: ");
        System.out.println(lijstReis);

        System.out.println();

        Map<Boolean, List<Reis>> opdelingReizen = reizenStream.stream().collect(Collectors.partitioningBy(
                a -> a.getGemiddeldeTemp() > 20));
        List<Reis> warm = opdelingReizen.get(true);
        List<Reis> koud = opdelingReizen.get(false);

        System.out.println("Sublist van reizen waarvan de gemiddelde temperatuur hoger is dan 25°C: ");
        warm.forEach(System.out::println);
        System.out.println();
        System.out.println("Sublist van reizen waarvan de gemiddelde temperatuur lager is dan 25°C: ");
        koud.forEach(System.out::println);
        System.out.println();
    }
}
