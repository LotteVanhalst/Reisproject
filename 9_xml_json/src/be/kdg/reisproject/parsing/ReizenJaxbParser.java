package be.kdg.reisproject.parsing;

import be.kdg.reisproject.model.Reis;
import be.kdg.reisproject.model.Reizen;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Lotte Vanhalst
 * @version 1.0 8/05/2019 15:33
 */
public class ReizenJaxbParser {
    public static void JaxbWriteXml(String file, Object root) {
        JAXBContext context = null;
        try {
            context = JAXBContext.newInstance(Reizen.class);

            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            marshaller.marshal(root, new File(file));
            System.out.println("File created!");

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public static <T> T JaxbReadXml(String file, Class<T> typeParameterClass) {
        T resultaat;
        try {
            JAXBContext context = JAXBContext.newInstance(typeParameterClass);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            File newFile = new File(file);

            resultaat = (T) unmarshaller.unmarshal(newFile);

            return resultaat;

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
}
