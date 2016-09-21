// 2016 © Yoan Blanc <yoan@dosimple.ch>, HES-SO
//
// Code adapted from:
// * http://javaxden.blogspot.ch/2007/08/xml-validation-with-dtd-in-java.html
// * http://www.javablog.fr/javaxml-validating-xml-with-dtd-document-type-definition-or-xsd-xml-schema-definition-with-jaxp-sax-dom-standard-apis.html

package ch.masrad.xml.dtd;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

public class ValidateXML {
    public static void main(String args[]) {
        try{
            SAXParserFactory factory = SAXParserFactory.newInstance();
            factory.setValidating(true);
            factory.setNamespaceAware(true);

            SAXParser parser = factory.newSAXParser();
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
