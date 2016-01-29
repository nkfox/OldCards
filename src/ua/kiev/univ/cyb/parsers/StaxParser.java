package ua.kiev.univ.cyb.parsers;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import ua.kiev.univ.cyb.oldCards.AuthorType;
import ua.kiev.univ.cyb.oldCards.CountryType;
import ua.kiev.univ.cyb.oldCards.TType;
import ua.kiev.univ.cyb.oldCards.ThemeType;
import ua.kiev.univ.cyb.oldCards.TypeCards;
import ua.kiev.univ.cyb.oldCards.ValType;

public class StaxParser {

    TypeCards cards = new TypeCards();
    TypeCards.Card card = new TypeCards.Card();
    int current = 0;

    private void startElement(XMLEvent event) {
        StartElement startElement = event.asStartElement();
        String qName = startElement.getName().getLocalPart();

        switch (qName) {
            case "oldCards":
                current = 1;
                break;
            case "oldCard": {
                card = new TypeCards.Card();
                Iterator<Attribute> attributes = startElement.getAttributes();
                card.setId(attributes.next().getValue());
                current = 2;
                break;
            }
            case "theme":
                current = 3;
                break;
            case "type":
                current = 4;
                break;
            case "sent":
                current = 5;
                break;
            case "country":
                current = 6;
                break;
            case "year":
                current = 7;
                break;
            case "valuable":
                current = 8;
                break;
            case "authors": {
                card.setAuthors(new AuthorType());
                current = 9;
                break;
            }
            case "painter":
                current = 10;
                break;
            case "poet":
                current = 11;
                break;
        }
    }

    private void characters(XMLEvent event) {
        Characters characters = event.asCharacters();
        String text = characters.getData();
        switch (current) {
            case 3: {
                card.setTheme(ThemeType.fromValue(text));
                current = 0;
                break;
            }
            case 4: {
                card.setType(TType.fromValue(text));
                current = 0;
                break;
            }
            case 5: {
                card.setSent(Boolean.valueOf(text));
                current = 0;
                break;
            }
            case 6: {
                card.setCountry(CountryType.fromValue(text));
                current = 0;
                break;
            }
            case 7: {
                card.setYear(Integer.valueOf(text));
                current = 0;
                break;
            }
            case 8: {
                card.setValuable(ValType.fromValue(text));
                current = 0;
                break;
            }
            case 10: {
                card.setPainter(text);
                current = 0;
                break;
            }
            case 11: {
                card.setPoet(text);
                current = 0;
                break;
            }

        }
    }

    private void endElement(XMLEvent event) {
        EndElement endElement = event.asEndElement();
        if (endElement.getName().getLocalPart().equalsIgnoreCase("oldCard")) {
            cards.addCard(card);
        }
        current = 0;
    }

    private void processEvent(XMLEvent event) {
        switch (event.getEventType()) {

            case XMLStreamConstants.START_ELEMENT:
                startElement(event);
                break;

            case XMLStreamConstants.CHARACTERS:
                characters(event);
                break;

            case XMLStreamConstants.END_ELEMENT:
                endElement(event);
                break;
        }
    }

    public TypeCards parcing() {
        cards = new TypeCards();
        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLEventReader eventReader = factory.createXMLEventReader(
                    new FileReader("src\\ua\\kiev\\univ\\cyb\\xml\\cards.xml"));

            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();
                processEvent(event);
            }
        } catch (FileNotFoundException | XMLStreamException e) {
        }

        return cards;
    }

}
