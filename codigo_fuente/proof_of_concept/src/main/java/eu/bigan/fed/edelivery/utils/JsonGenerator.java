package eu.bigan.fed.edelivery.utils;

import java.io.File;
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
    public String generateJson(String workerTask, String taskContent, String messageId) {
    	
    	final Logger logger = LogManager.getLogger(JsonGenerator.class);
        
    	JSONObject jsonObject = new JSONObject();
        jsonObject.put("worker_task", workerTask);
        jsonObject.put("taskContent", taskContent);
        String jsonContent = jsonObject.toString();
        
        String jsonFilePath = EnvParameters.getParameter("blueNodeJsonSource");
        jsonFilePath += "/" + messageId + ".json";
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
        
        return jsonFilePath;
    }

}
