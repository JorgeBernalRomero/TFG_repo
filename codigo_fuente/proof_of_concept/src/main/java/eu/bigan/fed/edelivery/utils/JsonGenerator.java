package eu.bigan.fed.edelivery.utils;

import org.json.JSONObject;

public class JsonGenerator {
    
    public String generateJson(String workerTask, String taskContent) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("worker_task", workerTask);
        jsonObject.put("taskContent", taskContent);
        
        return jsonObject.toString();
    }

}
