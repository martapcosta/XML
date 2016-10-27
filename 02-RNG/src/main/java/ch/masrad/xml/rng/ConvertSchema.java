// 2016 © Yoan Blanc <yoan@dosimple.ch>, HES-SO
//
// Code adapted from:
// * https://github.com/relaxng/jing-trang/blob/master/mod/trang/src/main/com/thaiopensource/relaxng/translate/Driver.java

package ch.masrad.xml.rng;

import java.io.File;
import java.io.IOException;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;

import com.thaiopensource.relaxng.edit.SchemaCollection;
import com.thaiopensource.relaxng.input.InputFailedException;
import com.thaiopensource.relaxng.input.InputFormat;
import com.thaiopensource.relaxng.input.parse.compact.CompactParseInputFormat;
import com.thaiopensource.relaxng.output.LocalOutputDirectory;
import com.thaiopensource.relaxng.output.OutputFailedException;
import com.thaiopensource.relaxng.output.OutputFormat;
import com.thaiopensource.relaxng.output.dtd.DtdOutputFormat;
import com.thaiopensource.relaxng.output.rng.RngOutputFormat;
import com.thaiopensource.relaxng.output.xsd.XsdOutputFormat;
import com.thaiopensource.relaxng.translate.util.InvalidParamsException;
import com.thaiopensource.resolver.Resolver;
import com.thaiopensource.xml.sax.ErrorHandlerImpl;

public class ConvertSchema {
	private static final String DEFAULT_OUTPUT_ENCODING = "UTF-8";
	private static final int DEFAULT_LINE_LENGTH = 72;
	private static final int DEFAULT_INDENT = 2;

	public static void convertRng(String sch, String rng)
			throws InputFailedException, InvalidParamsException, IOException,
			SAXException, OutputFailedException {
		File input = new File(sch);
		File output = new File(rng);
		String in = "rnc";
		String out = "rng";
		InputFormat inputFormat = new CompactParseInputFormat();
		OutputFormat outputFormat = new RngOutputFormat();

		Resolver resolver = null;
		ErrorHandler errorHandler = new ErrorHandlerImpl();
		String[] inputParams = new String[] {};
		String[] outputParams = new String[] {};

		SchemaCollection schemaCollection = inputFormat.load(
				input.toURI().toString(), inputParams, out, errorHandler,
				resolver);
		LocalOutputDirectory outputDirectory = new LocalOutputDirectory(
				schemaCollection.getMainUri(), output, out,
				DEFAULT_OUTPUT_ENCODING, DEFAULT_LINE_LENGTH, DEFAULT_INDENT);
		outputFormat.output(schemaCollection, outputDirectory, outputParams, in,
				errorHandler);
	}

	public static void convertXsd(String sch, String xsd)
			throws InputFailedException, InvalidParamsException, IOException,
			SAXException, OutputFailedException {
		File input = new File(sch);
		File output = new File(xsd);
		String in = "rnc";
		String out = "xsd";
		InputFormat inputFormat = new CompactParseInputFormat();
		OutputFormat outputFormat = new XsdOutputFormat();

		Resolver resolver = null;
		ErrorHandler errorHandler = new ErrorHandlerImpl();
		String[] inputParams = new String[] {};
		String[] outputParams = new String[] {};

		SchemaCollection schemaCollection = inputFormat.load(
				input.toURI().toString(), inputParams, out, errorHandler,
				resolver);
		LocalOutputDirectory outputDirectory = new LocalOutputDirectory(
				schemaCollection.getMainUri(), output, out,
				DEFAULT_OUTPUT_ENCODING, DEFAULT_LINE_LENGTH, DEFAULT_INDENT);
		outputFormat.output(schemaCollection, outputDirectory, outputParams, in,
				errorHandler);
	}

	public static void convertDtd(String sch, String dtd)
			throws InputFailedException, InvalidParamsException, IOException,
			SAXException, OutputFailedException {
		File input = new File(sch);
		File output = new File(dtd);
		String in = "rnc";
		String out = "dtd";
		InputFormat inputFormat = new CompactParseInputFormat();
		OutputFormat outputFormat = new DtdOutputFormat();

		Resolver resolver = null;
		ErrorHandler errorHandler = new ErrorHandlerImpl();
		String[] inputParams = new String[] {};
		String[] outputParams = new String[] {};

		SchemaCollection schemaCollection = inputFormat.load(
				input.toURI().toString(), inputParams, out, errorHandler,
				resolver);
		LocalOutputDirectory outputDirectory = new LocalOutputDirectory(
				schemaCollection.getMainUri(), output, out,
				DEFAULT_OUTPUT_ENCODING, DEFAULT_LINE_LENGTH, DEFAULT_INDENT);
		outputFormat.output(schemaCollection, outputDirectory, outputParams, in,
				errorHandler);
	}
}
