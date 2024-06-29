package eu.bigan.fed.edelivery.ops;

import java.io.File;
import java.util.concurrent.TimeUnit;
import eu.bigan.fed.edelivery.message.BiganFedListener;
import eu.bigan.fed.edelivery.utils.*;
import eu.bigan.fed.edelivery.message.BiganFedListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Texto sobre lo que hace esta clase.
 * @author jorgebernalromero
 *
 */
public class ExeScriptBash implements BiganFedListener{
    
    @Override
    /**
     * 
     */
    public void handleCallback(String taskContent, String messageId) {

        final Logger logger = LogManager.getLogger(ExeScriptBash.class);

        logger.info("Estoy dentro de la función de callback asignada 'exeScriptBash'");

        String outputCode = "";

        try {
            ProcessBuilder processBuilder = new ProcessBuilder("/bin/bash", taskContent);
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();
            int exitCode = process.waitFor();
            if(exitCode == 0) {
                outputCode = "0";
                logger.info("Correcta ejecución del script.");
            }
            else {
                outputCode = "1";
                logger.info("Incorrecta ejecución del script.");
            }
        } catch (Exception e) {
            logger.error("No se ha conseguido ejecutar el script.");
            e.printStackTrace();
        }

        String resultsPath = EnvParameters.getParameter("destDir");
        resultsPath += "/" + messageId + "/" + "outputs.txt";

        JsonGenerator jsonGenerator = new JsonGenerator();
		jsonGenerator.generateJson(resultsPath, outputCode, messageId);
    }
}
