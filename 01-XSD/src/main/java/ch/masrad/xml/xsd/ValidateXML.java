// 2016 © Yoan Blanc <yoan@dosimple.ch>, HES-SO
//
// Code adapted from:
// http://stackoverflow.com/questions/15732/whats-the-best-way-to-validate-an-xml-file-against-an-xsd-file
// http://www.edankert.com/validate.html
//
package ch.masrad.xml.xsd;

import static javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;

public class ValidateXML {

	static final String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";

	public static void main(String args[]) throws Exception {
		InputSource source = new InputSource("document.xml");

		SAXParserFactory factory = SAXParserFactory.newInstance();
		factory.setValidating(true);
		factory.setNamespaceAware(true);

		SAXParser parser = factory.newSAXParser();
		parser.setProperty(JAXP_SCHEMA_LANGUAGE, W3C_XML_SCHEMA_NS_URI);
		XMLReader reader = parser.getXMLReader();

		final int[] errors = new int[] { 0, 0 };
		reader.setErrorHandler(new ErrorHandler() {
			@Override
			public void fatalError(SAXParseException exception)
					throws SAXException {
				errors[0]++;
				System.out.printf("Line: %d%nFatal Error: %s %n",
						exception.getLineNumber(), exception.getMessage());
			}

			@Override
			public void error(SAXParseException e) throws SAXParseException {
				errors[0]++;
				System.out.printf("Line: %d%nError: %s%n", e.getLineNumber(),
						e.getMessage());
			}

			@Override
			public void warning(SAXParseException err)
					throws SAXParseException {
				errors[1]++;
				System.out.printf("Line: %d%nWarning: %s%n",
						err.getLineNumber(), "Warning: " + err.getMessage());
			}
		});
		reader.parse(source);

		if (errors[0] == 0) {
			System.out.println("Le document est valide.");
		} else {
			System.out.printf("Le document contient %d erreurs.%n", errors[0]);
			System.exit(1);
		}
	}
}
