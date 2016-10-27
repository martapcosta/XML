// 2016 © Yoan Blanc <yoan@dosimple.ch>, HES-SO
//
package ch.masrad.xml.rng;

public class Main {
	public static void main(String args[]) throws Exception {
		ValidateXML.validate("document.xml", "document.rnc");
		ConvertSchema.convertXsd("document.rnc", "document.rng");
		ConvertSchema.convertXsd("document.rnc", "document.xsd");
		ConvertSchema.convertDtd("document.rnc", "document.dtd");
	}
}