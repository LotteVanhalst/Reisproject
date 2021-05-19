package be.kdg.reisproject.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Lotte Vanhalst
 * @version 1.0 9/04/2019 20:16
 */
public class TestReizen {
    private Reizen reizen1;

    @Before
    public void setUp() throws Exception {
        reizen1 = new Reizen();
        reizen1.voegToe(new Reis("Cambodja", Werelddeel.AZIE, "15/10/2019", 15, 1876.24,"Khmer", "riel", 31, 250));
    }

    @Test
    public void testVoegToe(){
        reizen1.voegToe(new Reis("Rusland", Werelddeel.EUROPA, "01/08/2019", 10, 1234.56, "Russisch", "roebel", 22, 200));
        assertEquals("Er werd 1 reis toegevoegd.", 2, reizen1.getAantal());
    }

    @Test
    public void testVerwijder(){
        reizen1.verwijder("Cambodja", "15/10/2019", 15);
        assertEquals("Er werd 1 reis verwijderd.", 0, reizen1.getAantal());
    }
}