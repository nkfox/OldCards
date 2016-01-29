package ua.kiev.univ.cyb.parsers;

import ua.kiev.univ.cyb.oldCards.TypeCards;
import java.io.IOException;
import java.util.List;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Namespace;
import org.jdom2.input.DOMBuilder;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import ua.kiev.univ.cyb.oldCards.AuthorType;
import ua.kiev.univ.cyb.oldCards.CountryType;
import ua.kiev.univ.cyb.oldCards.TType;
import ua.kiev.univ.cyb.oldCards.ThemeType;
import ua.kiev.univ.cyb.oldCards.ValType;

public class DomParser {

    TypeCards cards = new TypeCards();

    public DomParser() {
    }

    public TypeCards parsing(String fileName) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        factory.setNamespaceAware(true);
        DocumentBuilder dombuilder = factory.newDocumentBuilder();
        org.w3c.dom.Document w3cDocument = dombuilder.parse(fileName);
        DOMBuilder jdomBuilder = new DOMBuilder();
        Document jdomDocument = jdomBuilder.build(w3cDocument);
        Element root = jdomDocument.getRootElement();

        List<Element> listCards = root.getChildren("oldCard", Namespace.getNamespace("http://ccc.com/Purchase"));
        
        for (Element card : listCards) {

            TypeCards.Card newCard = new TypeCards.Card();
            newCard.setId(card.getAttributeValue("id"));

            newCard.setTheme(ThemeType.fromValue(card.getChildText("theme", Namespace.getNamespace("http://ccc.com/Purchase"))));
            newCard.setType(TType.fromValue(card.getChildText("type", Namespace.getNamespace("http://ccc.com/Purchase"))));
            newCard.setSent(Boolean.parseBoolean(card.getChildText("sent", Namespace.getNamespace("http://ccc.com/Purchase"))));
            newCard.setCountry(CountryType.fromValue(card.getChildText("country", Namespace.getNamespace("http://ccc.com/Purchase"))));
            newCard.setYear(Integer.valueOf(card.getChildText("year", Namespace.getNamespace("http://ccc.com/Purchase"))));
            newCard.setValuable(ValType.fromValue(card.getChildText("valuable", Namespace.getNamespace("http://ccc.com/Purchase"))));

            Element authors = card.getChild("authors", Namespace.getNamespace("http://ccc.com/Purchase"));
            newCard.setAuthors(new AuthorType());

            String painter = authors.getChildText("painter", Namespace.getNamespace("http://aaa.com/Purchase"));
            newCard.setPainter(painter);

            String poet = authors.getChildText("poet", Namespace.getNamespace("http://aaa.com/Purchase"));
            newCard.setPoet(poet);

            cards.addCard(newCard);
        }
        return cards;
    }
}
