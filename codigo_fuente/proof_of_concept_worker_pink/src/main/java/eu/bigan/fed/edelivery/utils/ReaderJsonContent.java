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

    private static String taskContent;
    private static String workerTask;

    /**
     * 
     */
    public void readJsonContentFromFile(String filePath) {

        final Logger logger = LogManager.getLogger(ReaderJsonContent.class);

        try {
            String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));

            JSONObject jsonObject = new JSONObject(fileContent);

            taskContent = jsonObject.getString("taskContent");
            workerTask = jsonObject.getString("worker_task");

            logger.info("Se ha leído correctamente el archivo json.");

        } catch (IOException e) {
            logger.error("Se ha producido un error en la lectura del archivo json.");
            e.printStackTrace();
        }
    }

    /**
     * 
     */
    public String getTaskContent() {
		return taskContent;
	}

    /**
     * 
     */
    public String getWorkerTask() {
		return workerTask;
	}
}