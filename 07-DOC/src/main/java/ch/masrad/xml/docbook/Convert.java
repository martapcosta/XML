//2016 © Yoan Blanc <yoan@dosimple.ch>, HES-SO
//
//Code adapted from:
// https://github.com/spring-gradle-plugins/docbook-reference-plugin

package ch.masrad.xml.docbook;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.fop.apps.FOPException;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.apache.xerces.jaxp.SAXParserFactoryImpl;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;

import com.icl.saxon.TransformerFactoryImpl;

public class Convert {
	public static void main(String[] args) throws TransformerException,
			IOException, ParserConfigurationException, SAXException {
		String source = "document.xml";
		InputSource sourceFile = new InputSource(source);

		SAXParserFactory factory = new SAXParserFactoryImpl();
		factory.setValidating(true);
		factory.setNamespaceAware(true);
		factory.setFeature("http://apache.org/xml/features/validation/schema",
				true);
		factory.setFeature("http://apache.org/xml/features/xinclude", true);
		factory.setFeature(
				"http://apache.org/xml/features/xinclude/fixup-base-uris",
				true);
		factory.setFeature(
				"http://apache.org/xml/features/xinclude/fixup-language",
				false);

		// Catalog loader va faire le lien entre les schémas et nos fichiers
		// XSD.
		DocbookXMLCatalogResolver xmlResolver = buildResolver();

		// Validation XML
		SAXParser parser = factory.newSAXParser();
		parser.setProperty(
				"http://java.sun.com/xml/jaxp/properties/schemaLanguage",
				XMLConstants.W3C_XML_SCHEMA_NS_URI);
		XMLReader reader = parser.getXMLReader();

		final int[] errors = new int[] { 0, 0 };
		reader.setErrorHandler(new ErrorHandler() {
			public void fatalError(SAXParseException exception)
					throws SAXException {
				errors[0]++;
				System.out.printf("Line: %d%nFatal Error: %s %n",
						exception.getLineNumber(), exception.getMessage());
			}

			public void error(SAXParseException e) throws SAXParseException {
				errors[0]++;
				System.out.printf("Line: %d%nError: %s%n", e.getLineNumber(),
						e.getMessage());
			}

			public void warning(SAXParseException err)
					throws SAXParseException {
				errors[1]++;
				System.out.printf("Line: %d%nWarning: %s%n",
						err.getLineNumber(), err.getMessage());
			}
		});
		reader.setProperty(
				"http://apache.org/xml/properties/internal/entity-resolver",
				xmlResolver);
		reader.parse(sourceFile);

		if (errors[0] > 0) {
			System.out.printf("Le document contient %d erreurs.%n", errors[0]);
			System.exit(1);
		}

		// Transformation PDF et HTML
		toPdf("document.pdf", source, "src/main/xsl/pdf.xsl", reader);
		toHtml("document.html", source, "src/main/xsl/html.xsl", reader);
	}

	private static void toPdf(String dest, String src, String stylesheet,
			XMLReader reader)
			throws FOPException, TransformerException, IOException {
		File outputFile = new File(dest);
		InputSource sourceFile = new InputSource(src);
		Transformer transformer = buildTransformer(stylesheet);

		OutputStream out = null;
		try {
			out = new BufferedOutputStream(new FileOutputStream(outputFile));

			FopFactory fopFactory = FopFactory
					.newInstance(new File(".").toURI());
			Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, out);

			SAXResult res = new SAXResult(fop.getDefaultHandler());

			transformer.transform(new SAXSource(reader, sourceFile), res);
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

	private static void toHtml(String dest, String src, String stylesheet,
			XMLReader reader) throws TransformerException, IOException {
		File outputFile = new File(dest);
		InputSource sourceFile = new InputSource(src);
		Transformer transformer = buildTransformer(stylesheet);

		OutputStream out = null;
		try {
			out = new BufferedOutputStream(new FileOutputStream(outputFile));

			StreamResult res = new StreamResult(out);

			transformer.transform(new SAXSource(reader, sourceFile), res);
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

	private static DocbookXMLCatalogResolver buildResolver()
			throws IOException {
		ClassLoader classLoader = Convert.class.getClassLoader();
		List<String> catalogFiles = new ArrayList<String>();
		String docbookCatalogName = "docbook/catalog.xml";
		URL docbookCatalog = classLoader.getResource(docbookCatalogName);
		if (docbookCatalog == null) {
			throw new IllegalStateException(
					"Docbook catalog " + docbookCatalogName
							+ " could not be found in " + classLoader);
		}

		System.out.println(docbookCatalog.toExternalForm());
		catalogFiles.add(docbookCatalog.toExternalForm());

		Enumeration<URL> enumeration = classLoader.getResources("catalog.xml");
		while (enumeration.hasMoreElements()) {
			URL resource = (URL) enumeration.nextElement();
			catalogFiles.add(resource.toExternalForm());
			System.out.println(resource.toExternalForm());
		}

		DocbookXMLCatalogResolver xmlResolver = new DocbookXMLCatalogResolver();
		xmlResolver.setCatalogList(catalogFiles.toArray(new String[] {}));
		xmlResolver.setPreferPublic(true);
		return xmlResolver;
	}

	private static Transformer buildTransformer(String style)
			throws MalformedURLException, TransformerConfigurationException {
		Source stylesheet = new StreamSource(new File(style));
		TransformerFactory transformerFactory = new TransformerFactoryImpl();
		Transformer transformer = transformerFactory.newTransformer(stylesheet);
		transformer.setParameter("highlight.source", "1");
		transformer.setParameter("highlight.xslthl.config",
				new File("src/main/xsl/xslthl-config.xml").toURI().toURL());

		return transformer;
	}
}
