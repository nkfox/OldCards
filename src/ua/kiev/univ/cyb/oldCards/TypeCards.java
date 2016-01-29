package ua.kiev.univ.cyb.oldCards;

import java.util.ArrayList;
import java.util.List;

public class TypeCards {

    protected List<Card> cardList = new ArrayList<>();

    public List<Card> getCard() {
        return this.cardList;
    }

    public void addCard(Card card) {
        cardList.add(card);
    }

    public static class Card {

        protected ThemeType theme;
        protected TType type;
        protected boolean sent;
        protected CountryType country;
        protected int year;
        protected ValType valuable;

        protected AuthorType authors;

        protected String id;

        public ThemeType getTheme() {
            return theme;
        }

        public void setTheme(ThemeType theme) {
            this.theme = theme;
        }

        public TType getType() {
            return type;
        }

        public void setType(TType type) {
            this.type = type;
        }

        public boolean isSent() {
            return sent;
        }

        public void setSent(boolean sent) {
            this.sent = sent;
        }

        public CountryType getCountry() {
            return country;
        }

        public void setCountry(CountryType country) {
            this.country = country;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public ValType getValuable() {
            return valuable;
        }

        public void setValuable(ValType valuable) {
            this.valuable = valuable;
        }

        public AuthorType getAuthors() {
            return authors;
        }

        public void setAuthors(AuthorType authors) {
            this.authors = authors;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setPainter(String painter) {
            this.authors.setPainter(painter);
        }

        public void setPoet(String poet) {
            this.authors.setPoet(poet);
        }

    }

}
