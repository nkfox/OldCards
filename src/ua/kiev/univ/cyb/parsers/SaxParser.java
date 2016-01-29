package ua.kiev.univ.cyb.parsers;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import ua.kiev.univ.cyb.oldCards.AuthorType;
import ua.kiev.univ.cyb.oldCards.CountryType;
import ua.kiev.univ.cyb.oldCards.TType;
import ua.kiev.univ.cyb.oldCards.ThemeType;
import ua.kiev.univ.cyb.oldCards.TypeCards;
import ua.kiev.univ.cyb.oldCards.ValType;

public class SaxParser extends DefaultHandler {

    TypeCards cards = new TypeCards();
    TypeCards.Card card;
    int current = 0;

    public SaxParser() {
    }

    @Override
    public void startDocument() {
        System.out.println("start parsing...");
    }

    @Override
    public void endDocument() {
        System.out.println("end parsing");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attr) {
        switch (qName) {
            case "oldCards":
                current = 1;
                break;
            case "oldCard": {
                card = new TypeCards.Card();
                card.setId(attr.getValue(0));
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
            case "myspace:painter":
                current = 10;
                break;
            case "myspace:poet":
                current = 11;
                break;

        }
    }

    @Override
    public void characters(char[] buf, int start, int length) {
        String text = new String(buf, start, length);
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

    @Override
    public void endElement(String uri, String localName, String qName) {
        switch (qName) {
            case "oldCard": {
                cards.addCard(card);
            }
        }
        current = 0;
    }

    public TypeCards getList() {
        return cards;
    }
}
