// 2016 © Yoan Blanc <yoan@dosimple.ch>, HES-SO
//
// Code adapted from:
// http://stackoverflow.com/questions/15732/whats-the-best-way-to-validate-an-xml-file-against-an-xsd-file
// http://www.edankert.com/validate.html

package ch.masrad.xml.xsd;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import javax.xml.XMLConstants;

import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

public class ValidateXML {
    public static void main(String args[]) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            factory.setValidating(true);
            factory.setNamespaceAware(true);

            SAXParser parser = factory.newSAXParser();
            parser.setProperty("http://java.sun.com/xml/jaxp/properties/schemaLanguage",
                    XMLConstants.W3C_XML_SCHEMA_NS_URI);
            XMLReader reader = parser.getXMLReader();

            ErrorPrinter errorHandler = new ErrorPrinter();

            reader.setErrorHandler((ErrorHandler) errorHandler);
            reader.parse(new InputSource("document.xml"));

            if (errorHandler.getErrors() == 0) {
                System.out.println("Le document est valide.");
            } else {
                System.out.printf(
                    "Le document contient %d erreurs.%n",
                    errorHandler.getErrors());
                System.exit(1);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            System.exit(2);
        }
    }
}
