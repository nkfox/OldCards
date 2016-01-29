package main;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;
import ua.kiev.univ.cyb.parsers.SaxParser;
import ua.kiev.univ.cyb.oldCards.TypeCards;
import ua.kiev.univ.cyb.parsers.ChangingXML;
import ua.kiev.univ.cyb.parsers.DomParser;
import ua.kiev.univ.cyb.parsers.StaxParser;
import ua.kiev.univ.cyb.parsers.Validation;

public class Main {

    public static void print(TypeCards list) {
        for (TypeCards.Card card : list.getCard()) {
            System.out.print("card " + card.getId());
            System.out.print(",     theme " + card.getTheme());
            System.out.print(",     type " + card.getType());
            System.out.print(",     sent " + card.isSent());
            System.out.print(",     country " + card.getCountry());
            System.out.print(",     year " + card.getYear());
            System.out.println(",     valuable " + card.getValuable());
            System.out.print("     authors:");
            System.out.print("     painter " + card.getAuthors().getPainter());
            System.out.println(",     poet " + card.getAuthors().getPoet());
            System.out.println();
        }
    }

    public static void sax() {
        try {
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            SaxParser par = new SaxParser();
            parser.parse("src\\ua\\kiev\\univ\\cyb\\xml\\cards.xml", par);
            TypeCards list = par.getList();
            print(list);

        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void stax() {

        StaxParser par = new StaxParser();
        TypeCards list = par.parcing();
        print(list);
    }

    public static void dom() {
        try {
            DomParser par = new DomParser();
            TypeCards list = par.parsing("src\\ua\\kiev\\univ\\cyb\\xml\\cards.xml");
            print(list);
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {

        //saxParser
        System.out.println("SAX Parsing");
        sax();

        //staxParser
        System.out.println("\nStAX Parsing");
        stax();

        //domParser
        System.out.println("\nDOM Parsing");
        dom();

        //validation
        System.out.println("\nValidation");
        System.out.println(Validation.validation("src\\ua\\kiev\\univ\\cyb\\xml\\cards.xml",
                "src\\ua\\kiev\\univ\\cyb\\xml\\cards.xsd"));

        //changing xml
        System.out.println("\nchanging xml");
        ChangingXML.changeXML("src\\ua\\kiev\\univ\\cyb\\xml\\cards.xml", "c001", "theme", "sport");
        dom();
    }

}
