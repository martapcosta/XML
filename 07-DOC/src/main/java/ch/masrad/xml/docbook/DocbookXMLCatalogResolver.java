//2016 © Yoan Blanc <yoan@dosimple.ch>, HES-SO
//
// This is a very ugly hack to resolve the Docbook namespace...
//
package ch.masrad.xml.docbook;

import java.io.IOException;

import org.apache.xerces.util.XMLCatalogResolver;
import org.apache.xerces.xni.XMLResourceIdentifier;
import org.apache.xerces.xni.XNIException;
import org.apache.xerces.xni.parser.XMLEntityResolver;
import org.apache.xerces.xni.parser.XMLInputSource;

public class DocbookXMLCatalogResolver implements XMLEntityResolver {
	public DocbookXMLCatalogResolver() {
		resolver = new XMLCatalogResolver();
	}

	public void setCatalogList(String[] catalogs) {
		resolver.setCatalogList(catalogs);
	}

	public void setPreferPublic(boolean preferPublic) {
		resolver.setPreferPublic(preferPublic);
	}

	public XMLInputSource resolveEntity(
			XMLResourceIdentifier resourceIdentifier)
			throws IOException, XNIException {
		String namespace = resourceIdentifier.getNamespace();
		boolean isDocBook = namespace != null
				&& namespace.equals("http://docbook.org/ns/docbook");
		if (isDocBook) {
			resourceIdentifier
					.setNamespace("http://docbook.org/xml/5.0/xsd/docbook.xsd");
		}
		XMLInputSource source = resolver.resolveEntity(resourceIdentifier);
		if (isDocBook) {
			resourceIdentifier.setNamespace("http://docbook.org/ns/docbook");
		}
		return source;
	}

	private XMLCatalogResolver resolver;
}
