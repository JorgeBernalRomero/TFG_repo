package eu.bigan.fed.edelivery.utils;

import java.io.File;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;


/**
 * Texto sobre lo que hace esta clase.
 * @author jorgebernalromero
 *
 */
public class JsonGenerator {
    
	/**
	 * 
	 * @param workerTask
	 * @param taskContent
	 * @param messageId
	 * @return
	 */
    public void generateJson(String resultsPath, String outputCode, String messageId) {
    	
    	final Logger logger = LogManager.getLogger(JsonGenerator.class);

        String results = "";
        
        if(resultsPath != ""){
            try {
                results = FileManager.readFileAsString(resultsPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            results = resultsPath;
        }

    	JSONObject jsonObject = new JSONObject();
        jsonObject.put("results", results);
        jsonObject.put("outputCode", outputCode);
        String jsonContent = jsonObject.toString();
        
        String jsonFilePath = EnvParameters.getParameter("destDir");
        jsonFilePath += "/" + messageId + "/" + "returnJson.json";
        File jsonFile = new File(jsonFilePath);

        byte[] payloadBytes = jsonContent.getBytes();
        
        try {
        	FileManager.writeBytesToFile(jsonFile, payloadBytes);
        	logger.info("Json generado correctamente.");
        }
        catch (Exception e){
        	logger.error("Ha habido un problema generando el json.");
            e.printStackTrace();
        }
    }
}