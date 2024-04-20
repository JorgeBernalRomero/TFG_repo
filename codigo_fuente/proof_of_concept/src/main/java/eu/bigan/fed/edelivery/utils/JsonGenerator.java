package eu.bigan.fed.edelivery.utils;

import java.io.File;
import org.json.JSONObject;

public class JsonGenerator {
    
    public String generateJson(String workerTask, String taskContent, String messageId) {
        
	    	JSONObject jsonObject = new JSONObject();
	        jsonObject.put("worker_task", workerTask);
	        jsonObject.put("taskContent", taskContent);
	        String jsonContent = jsonObject.toString();
	        
	        String jsonFilePath = EnvParameters.getParameter("blueNodeJsonSource");
	        jsonFilePath += "/" + messageId + ".json";
	        System.out.println(jsonFilePath);
	        File jsonFile = new File(jsonFilePath);
	
	        byte[] payloadBytes = jsonContent.getBytes();
	        
	        try {
	        FileManager.writeBytesToFile(jsonFile, payloadBytes);
	        System.out.println("JSON file created: " + jsonFilePath);
	        
	        }
	        catch (Exception e){
	            e.printStackTrace();
	        }
	        
	        return jsonFilePath;
    }

}
