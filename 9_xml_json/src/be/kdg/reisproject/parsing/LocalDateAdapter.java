package be.kdg.reisproject.parsing;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;

/**
 * @author Lotte Vanhalst
 * @version 1.0 8/05/2019 15:21
 */
public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {

    public LocalDate unmarshal(String myString) throws Exception {
        return LocalDate.parse(myString);
    }

    public String marshal(LocalDate myDate) throws Exception {
        return myDate.toString();
    }
}
