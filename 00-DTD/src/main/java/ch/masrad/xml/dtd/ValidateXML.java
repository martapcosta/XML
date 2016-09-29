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
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;

public class ValidateXML {
	public static void main(String args[]) throws Exception {
		InputSource source = new InputSource("document.xml");

		SAXParserFactory factory = SAXParserFactory.newInstance();
		factory.setValidating(true);
		factory.setNamespaceAware(true);

		SAXParser parser = factory.newSAXParser();
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
