package be.kdg.reisproject.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

/**
 * @author Lotte Vanhalst
 * @version 1.0 10/04/2019 1:08
 */
public class ReisFactory {
    private static final String ABC1 = "AEIOULNMPNKSDVT";
    private static final String ABC2 = "aeioulnmpnksdvt";
    private static Random random = new Random();

    private ReisFactory(){
    }

    public static Reis newEmptyReis(){
        return new Reis();
    }

    public static Reis newFilledReis(String naam, Werelddeel werelddeel, LocalDate begindatum, int aantalDagen, double kostprijs, String taal, String munteenheid, double gemiddeldeTemp, double zakgeld){
        return new Reis(naam, werelddeel, begindatum, aantalDagen, kostprijs, taal, munteenheid, gemiddeldeTemp, zakgeld);
    }

    public static Reis basisFilledReis(String naam, LocalDate begindatum, int aantalDagen, double kostprijs){
        return new Reis(naam, begindatum, aantalDagen, kostprijs);
    }

    public static Reis newRandomReis(){
        String naam = generateString(random.nextInt(10)+5, 1, false);
        int index = random.nextInt(Werelddeel.values().length);
        Werelddeel werelddeel =  Werelddeel.values()[index];
        LocalDate begindatum = LocalDate.now().plusDays(random.nextInt(700));
        int aantalDagen = random.nextInt(23) + 7;
        double kostprijs = (aantalDagen/7*500) + ((aantalDagen/7*2500) - (aantalDagen/7*500))*random.nextDouble();
        String taal = generateString(random.nextInt(5)+5, 1, false);
        String munteenheid = generateString(random.nextInt(4)+3, 1, false).toLowerCase();
        double gemiddeldetemp = -15 + (40 - (-15))*random.nextDouble();
        double zakgeld = 700*random.nextDouble();
        return new Reis(naam, werelddeel, begindatum, aantalDagen, kostprijs, taal, munteenheid, gemiddeldetemp, zakgeld);
    }

    private static String generateString(int maxWordLength, int WordCount, boolean camelCase){
        StringBuilder sb = new StringBuilder();
        sb.append(ABC1.charAt(random.nextInt(ABC1.length())));
        for( int i = 1; i < maxWordLength; i++)
            sb.append(ABC2.charAt(random.nextInt(ABC2.length())));
        return sb.toString();
    }
}
