//2016 © Yoan Blanc <yoan@dosimple.ch>, HES-SO
//
//Code adapted from:
// https://xmlgraphics.apache.org/fop/2.1/embedding.html
//
package ch.masrad.xml.fop;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.fop.apps.FOPException;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;

public class Convert {
	public static void main(String[] args)
			throws FOPException, TransformerException, FileNotFoundException {
		OutputStream out = null;

		StreamSource src = new StreamSource(new File("document.fo"));
		out = new BufferedOutputStream(
				new FileOutputStream(new File("document.pdf")));

		FopFactory fopFactory = FopFactory.newInstance(new File(".").toURI());
		Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, out);

		TransformerFactory factory = TransformerFactory.newInstance();
		Transformer transformer = factory.newTransformer();

		SAXResult res = new SAXResult(fop.getDefaultHandler());

		transformer.transform(src, res);
	}
}
