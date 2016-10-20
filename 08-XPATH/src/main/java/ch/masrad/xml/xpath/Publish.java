// 2016 © Yoan Blanc <yoan@dosimple.ch>, HES-SO
//
// Code inspired from:
// * http://stackoverflow.com/questions/6875807/convert-svg-to-pdf
//
package ch.masrad.xml.xpath;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.batik.transcoder.Transcoder;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.fop.svg.PDFTranscoder;

import com.kitfox.svg.SVGDiagram;
import com.kitfox.svg.SVGException;
import com.kitfox.svg.SVGUniverse;

public class Publish {
	public static void main(String[] args) throws TranscoderException,
			TransformerException, IOException, SVGException {

		String xml = "document.xml";
		String xsl = "document.xsl";
		String svg = "document.svg"; // voir modèle.svg
		String pdf = "document.pdf";
		String png = "document.png";

		// Configuration du moteur XSLT
		System.setProperty("javax.xml.transform.TransformerFactory",
				"org.apache.xalan.processor.TransformerFactoryImpl");

		// Production du fichier SVG
		TransformerFactory factory = TransformerFactory.newInstance();
		Transformer transformer = factory.newTransformer(new StreamSource(xsl));

		transformer.transform(new StreamSource(new FileInputStream(xml)),
				new StreamResult(new FileOutputStream(svg)));

		// Production du fichier PDF avec Apache Batik
		Transcoder transcoder = new PDFTranscoder();
		transcoder.transcode(new TranscoderInput(new FileInputStream(svg)),
				new TranscoderOutput(new FileOutputStream(pdf)));

		// Production du fichier PNG avec SVG Salamander
		SVGUniverse universe = new SVGUniverse();
		SVGDiagram diagram = universe
				.getDiagram(universe.loadSVG(new FileInputStream(svg), svg));
		BufferedImage image = new BufferedImage(400, 400,
				BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = image.createGraphics();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		diagram.render(g2d);
		g2d.dispose();
		ImageIO.write(image, "PNG", new File(png));
	}
}
