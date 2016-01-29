package ua.kiev.univ.cyb.parsers;

import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.SAXParseException;

class MyErrorHandler implements ErrorHandler {

    boolean flag = true;

    @Override
    public void warning(SAXParseException e) {
        System.err.println("warning: " + getLineAddress(e) + "-" + e.getMessage());
    }

    @Override
    public void error(SAXParseException e) {
        flag = false;
        System.err.println((getLineAddress(e) + " - " + e.getMessage()));
    }

    @Override
    public void fatalError(SAXParseException e) {
        flag = false;
        System.err.println(getLineAddress(e) + " - " + e.getMessage());
    }

    private String getLineAddress(SAXParseException e) {
        return e.getLineNumber() + " : " + e.getColumnNumber();
    }
}

public class Validation {

    public static boolean validation(String xmlFile, String xsdFile) {
        DOMParser parser = new DOMParser();
        MyErrorHandler handler = new MyErrorHandler();
        try {

            parser.setErrorHandler(handler);
            parser.setFeature("http://xml.org/sax/features/validation", true);
            parser.setFeature("http://apache.org/xml/features/validation/schema", true);
            parser.setFeature("http://apache.org/xml/features/validation/schema-full-checking", true);
            //parser.setProperty("http://apache.org/xml/properties/schema/external-schemaLocation", xsdFile);

            parser.parse(xmlFile);

        } catch (SAXNotRecognizedException e) {
        } catch (SAXNotSupportedException ex) {
        } catch (SAXException | IOException ex) {
        }
        return handler.flag;
    }

}
