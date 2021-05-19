package be.kdg.reisproject.parsing;

import be.kdg.reisproject.model.Reis;
import be.kdg.reisproject.model.Reizen;
import com.sun.xml.internal.txw2.output.IndentingXMLStreamWriter;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Lotte Vanhalst
 * @version 1.0 8/05/2019 12:30
 */
public class ReizenStaxParser {
    private XMLStreamWriter xmlStreamWriter;
    private Reizen reizen;

    public ReizenStaxParser(Reizen reizen,String path){
        this.reizen = reizen;
        XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newInstance();
        try {
            xmlStreamWriter = new IndentingXMLStreamWriter(xmlOutputFactory.createXMLStreamWriter
                    (new FileWriter(path)));
            xmlStreamWriter = new IndentingXMLStreamWriter(xmlStreamWriter);
        } catch (XMLStreamException | IOException e) {
            e.printStackTrace();
        }
    }

    public void writeXML(){
        try {
            xmlStreamWriter.writeStartDocument();
            xmlStreamWriter.writeStartElement("Reizen");

            for (Reis reis : reizen.getBeschikbareReizen()) {
                xmlStreamWriter.writeStartElement("reis");

                xmlStreamWriter.writeAttribute("werelddeel", String.valueOf(reis.getWerelddeel()));
                writeSimpleElement(xmlStreamWriter, "naam", reis.getNaam());
                writeSimpleElement(xmlStreamWriter, "begindatum", String.valueOf(reis.getBegindatum()));
                writeSimpleElement(xmlStreamWriter, "aantal_dagen", String.valueOf(reis.getAantalDagen()));
                writeSimpleElement(xmlStreamWriter, "kostprijs", String.valueOf(reis.getKostprijs()));
                writeSimpleElement(xmlStreamWriter, "taal", reis.getTaal());
                writeSimpleElement(xmlStreamWriter, "munteenheid", reis.getMunteenheid());
                writeSimpleElement(xmlStreamWriter, "gemiddelde_temperatuur", String.valueOf(reis.getGemiddeldeTemp()));
                writeSimpleElement(xmlStreamWriter, "zakgeld", String.valueOf(reis.getZakgeld()));

                xmlStreamWriter.writeEndElement(); // </person>
            }

            xmlStreamWriter.writeEndElement(); // </family>
            xmlStreamWriter.writeEndDocument();

            xmlStreamWriter.close();

            System.out.println("File saved!");
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }

    private static void writeSimpleElement(XMLStreamWriter xmlStreamWriter, String naam, String inhoud) throws XMLStreamException {
        xmlStreamWriter.writeStartElement(naam);
        xmlStreamWriter.writeCharacters(inhoud);
        xmlStreamWriter.writeEndElement();
    }
}
