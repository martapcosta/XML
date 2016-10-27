package ch.masrad.xml.json;

import java.io.IOException;

import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;

public class Validate {
	public static void main(String[] args)
			throws ProcessingException, IOException {
		// JSON
		String json = "document.json";
		String schema = "schema.json";

		JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
		JsonSchema jsonSchema = factory
				.getJsonSchema(JsonLoader.fromPath(schema));
		ProcessingReport report = jsonSchema
				.validate(JsonLoader.fromPath(json));
		System.out.println(report);
	}
}