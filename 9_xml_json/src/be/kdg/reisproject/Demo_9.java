package be.kdg.reisproject;

import be.kdg.reisproject.data.Data;
import be.kdg.reisproject.model.Reis;
import be.kdg.reisproject.model.Reizen;
import be.kdg.reisproject.parsing.ReizenDomParser;
import be.kdg.reisproject.parsing.ReizenGsonParser;
import be.kdg.reisproject.parsing.ReizenJaxbParser;
import be.kdg.reisproject.parsing.ReizenStaxParser;

/**
 * @author Lotte Vanhalst
 * @version 1.0 8/05/2019 12:23
 */
public class Demo_9 {
    public static void main(String[] args) {
        Data lijst = new Data();
        Reizen reizen = new Reizen();
        Data.geefReizen().forEach(reizen::voegToe);

        /*ReizenStaxParser staxParser = new ReizenStaxParser(reizen, "9_xml_json/files/staxReizen.xml");
        staxParser.writeXML();

        System.out.println("\nNa wegschrijven via STAX / inlezen via DOM:");
        System.out.println();
        ReizenDomParser.domReadXml("9_xml_json/files/staxReizen.xml");

        System.out.println();*/

        ReizenJaxbParser.JaxbWriteXml("9_xml_json/files/jaxbReizen.xml", reizen);

        reizen.getReisList().forEach(System.out::println);

        System.out.println("\nNa wegschrijven / inlezen via JAXB:");
        System.out.println();
        ReizenJaxbParser.JaxbReadXml("9_xml_json/files/jaxbReizen.xml", Reizen.class).getReisList().forEach(System.out::println);

        /*System.out.println();

        ReizenGsonParser.writeJson(reizen, "9_xml_json/files/reizen.json");

        System.out.println("\nNa wegschrijven / inlezen via GSON::");
        System.out.println();
        ReizenGsonParser.readJso("9_xml_json/files/reizen.json");*/


    }
}
