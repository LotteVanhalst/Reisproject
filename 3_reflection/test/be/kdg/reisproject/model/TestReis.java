package be.kdg.reisproject.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Lotte Vanhalst
 * @version 1.0 9/04/2019 19:38
 */
public class TestReis {
    private Reis reis1;
    private Reis reis2;

    @Before
    public void setUp() throws Exception {
        reis1 = new Reis("Rusland", Werelddeel.EUROPA, "01/08/2019", 10, 1234.56, "Russisch", "roebel", 22, 200);
        reis2 = new Reis("Cambodja", Werelddeel.AZIE, "15/10/2019", 15, 1876.24,"Khmer", "riel", 31, 250);
    }

    @Test
    public void testEquals(){
        Reis reis3 = new Reis ("Cambodja", Werelddeel.AZIE, "15/10/2019", 15, 1567.54, "Khmer", "riel", 31, 250);
        assertEquals("De reizen zijn gelijk als hun naam, begindatum en aantal dagen gelijk zijn.", reis2, reis3);
        assertNotEquals("De reizen zijn verschillend.", reis1, reis3);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testOngeldig(){
        reis1.setKostprijs(321.43);
        fail("De kostprijs moet minstens 1000 euro zijn");
    }

    @Test
    public void testGeldig(){
        reis1.setKostprijs(1130.43);
    }

    @Test
    public void testCompareTo(){
        assertEquals("Reis1 staat hoger dan reis2",15, reis1.compareTo(reis2));
    }

    @Test
    public void testKostprijs(){
        assertEquals("Het verschil tussen de kostprijs van reis2 en reis1 is 600 euro met een tolerantie van 50 euro",
                600, (reis2.getKostprijs()-reis1.getKostprijs()),50);
    }
}
