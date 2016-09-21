// 2016 © Yoan Blanc <yoan@dosimple.ch>, HES-SO
//
// Code adapted from:
// http://stackoverflow.com/questions/15732/whats-the-best-way-to-validate-an-xml-file-against-an-xsd-file
// http://stackoverflow.com/a/2104332

package ch.masrad.xml.rng;

import java.io.File;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.ErrorHandler;

public class ValidateXML {
    public static void main(String args[]) {
        try {
            System.setProperty(SchemaFactory.class.getName() + ":" + XMLConstants.RELAXNG_NS_URI, "com.thaiopensource.relaxng.jaxp.CompactSyntaxSchemaFactory");
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.RELAXNG_NS_URI);

            Source schemaFile = new StreamSource(new File("document.rnc"));
            Schema schema = factory.newSchema(schemaFile);

            Validator validator = schema.newValidator();

            ErrorPrinter errorHandler = new ErrorPrinter();
            validator.setErrorHandler((ErrorHandler) errorHandler);

            Source document = new StreamSource(new File("document.xml"));

            validator.validate(document);

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
