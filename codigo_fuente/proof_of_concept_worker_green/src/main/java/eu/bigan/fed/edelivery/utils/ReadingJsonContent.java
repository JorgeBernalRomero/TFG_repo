package eu.bigan.fed.edelivery.utils;

import org.json.*;
import java.io.IOException;
import java.nio.file.*;

public class ReadingJsonContent {

    private static String taskContent;
    private static String workerTask;


    public void readingJsonContentFromFile(String filePath) {
        try {
            // Read the entire file content into a String
            String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));

            // Convert file content to JSONObject
            JSONObject jsonObject = new JSONObject(fileContent);

            // Extract values for "taskContent" and "worker_task"
            taskContent = jsonObject.getString("taskContent");
            workerTask = jsonObject.getString("worker_task");

        } catch (IOException e) {
            System.err.println("Error reading file: " + filePath);
            e.printStackTrace();
        }
    }

    public String getTaskContent() {
		return taskContent;
	}

    public String getWorkerTask() {
		return workerTask;
	}

}
