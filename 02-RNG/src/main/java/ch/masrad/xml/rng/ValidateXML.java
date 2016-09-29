// 2016 © Yoan Blanc <yoan@dosimple.ch>, HES-SO
//
// Code adapted from:
// http://stackoverflow.com/questions/15732/whats-the-best-way-to-validate-an-xml-file-against-an-xsd-file
// http://stackoverflow.com/a/2104332
//
package ch.masrad.xml.rng;

import static javax.xml.XMLConstants.RELAXNG_NS_URI;

import java.io.File;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class ValidateXML {

	static final String RELAXNG_COMPACT_SYNTAX = "com.thaiopensource.relaxng.jaxp.CompactSyntaxSchemaFactory";

	public static void main(String args[]) throws Exception {
		File source = new File("document.xml");

		System.setProperty(SchemaFactory.class.getName() + ":" + RELAXNG_NS_URI,
				RELAXNG_COMPACT_SYNTAX);
		SchemaFactory factory = SchemaFactory.newInstance(RELAXNG_NS_URI);

		Source schemaFile = new StreamSource(new File("document.rnc"));
		Schema schema = factory.newSchema(schemaFile);

		Validator validator = schema.newValidator();

		final int[] errors = new int[] { 0, 0 };
		validator.setErrorHandler(new ErrorHandler() {
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

		Source document = new StreamSource(source);

		validator.validate(document);

		if (errors[0] == 0) {
			System.out.println("Le document est valide.");
		} else {
			System.out.printf("Le document contient %d erreurs.%n", errors[0]);
			System.exit(1);
		}
	}
}
