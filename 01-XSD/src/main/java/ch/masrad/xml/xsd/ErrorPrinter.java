package ch.masrad.xml.xsd;

import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.ErrorHandler;

public class ErrorPrinter implements ErrorHandler {
    public ErrorPrinter() {
        errors = 0;
        warnings = 0;
    }

    public int getErrors() {
        return errors;
    }

    public int getWarnings() {
        return warnings;
    }

    public void fatalError(SAXParseException exception) throws SAXException {
        errors++;
        System.out.printf(
                "Line: %d%nFatal Error: %s %n",
                exception.getLineNumber(),
                exception.getMessage());
    }

    public void error(SAXParseException e) throws SAXParseException {
        errors++;
        System.out.printf(
                "Line: %d%nError: %s%n",
                e.getLineNumber(),
                e.getMessage());
    }

    public void warning(SAXParseException err) throws SAXParseException {
        warnings++;
        System.out.printf(
                "Line: %d%nWarning: %s%n",
                err.getLineNumber(),
                "Warning: "+err.getMessage());
    }

    private int errors;
    private int warnings;
}
