package eu.bigan.fed.edelivery.utils;

import org.json.*;
import java.io.IOException;
import java.nio.file.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Texto sobre lo que hace esta clase.
 * @author jorgebernalromero
 *
 */
public class ReaderJsonContent {

    private static String results;
    private static String outputCode;

    /**
     * 
     */
    public void readJsonContentFromFile(String filePath) {

        final Logger logger = LogManager.getLogger(ReaderJsonContent.class);

        try {
            String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));

            JSONObject jsonObject = new JSONObject(fileContent);

            results = jsonObject.getString("results");
            outputCode = jsonObject.getString("outputCode");

            logger.info("Se ha leído correctamente el archivo json.");

        } catch (IOException e) {
            logger.error("Se ha producido un error en la lectura del archivo json.");
            e.printStackTrace();
        }
    }

    /**
     * 
     */
    public String getResults() {
		return results;
	}

    /**
     * 
     */
    public String getOutputCode() {
		return outputCode;
	}

}
