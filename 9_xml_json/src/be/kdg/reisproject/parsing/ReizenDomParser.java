package be.kdg.reisproject.parsing;

import be.kdg.reisproject.model.Reis;
import be.kdg.reisproject.model.Reizen;
import be.kdg.reisproject.model.Werelddeel;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * @author Lotte Vanhalst
 * @version 1.0 8/05/2019 13:03
 */
public class ReizenDomParser{

    public static Reizen domReadXml (String fileName){
        Reizen reizen = new Reizen();
        try {
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(fileName));

            Element rootElement = doc.getDocumentElement();
            NodeList personNodes = rootElement.getChildNodes();
            for (int i = 0; i < personNodes.getLength(); i++) {
                if (personNodes.item(i).getNodeType() != Node.ELEMENT_NODE) {
                    continue;
                }
                Element e = (Element) personNodes.item(i);
                Element naam = (Element) e.getElementsByTagName("naam").item(0);
                String attribuut = e.getAttribute("werelddeel");
                Element begindatum = (Element) e.getElementsByTagName("begindatum").item(0);
                Element aantalDagen = (Element) e.getElementsByTagName("aantal_dagen").item(0);
                Element kostprijs = (Element) e.getElementsByTagName("kostprijs").item(0);
                Element taal = (Element) e.getElementsByTagName("taal").item(0);
                Element munteenheid = (Element) e.getElementsByTagName("munteenheid").item(0);
                Element gemiddeldeTemp = (Element) e.getElementsByTagName("gemiddelde_temperatuur").item(0);
                Element zakgeld = (Element) e.getElementsByTagName("zakgeld").item(0);

                reizen.voegToe(new Reis(
                        naam.getTextContent(),
                        Werelddeel.valueOf(attribuut),
                        LocalDate.parse(begindatum.getTextContent()),
                        Integer.parseInt(aantalDagen.getTextContent()),
                        Double.parseDouble(kostprijs.getTextContent()),
                        taal.getTextContent(),
                        munteenheid.getTextContent(),
                        Double.parseDouble(gemiddeldeTemp.getTextContent()),
                        Double.parseDouble(zakgeld.getTextContent())
                ));
            }
            reizen.getBeschikbareReizen().forEach(System.out::println);
        } catch (IOException | ParserConfigurationException | SAXException e) {
            e.printStackTrace();
        }
        return reizen;
    }
}
