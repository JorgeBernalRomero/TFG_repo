package eu.bigan.fed.edelivery.ops;

import java.io.File;
import java.io.FileOutputStream;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import eu.bigan.fed.edelivery.message.BiganFedListener;
import eu.bigan.fed.edelivery.utils.EnvParameters;
import eu.bigan.fed.edelivery.utils.FileManager;
import eu.bigan.fed.edelivery.utils.JsonGenerator;


public class GitHubRepoCloner implements BiganFedListener {

    public void handleCallback(String taskContent, String messageId) {
        final Logger logger = LogManager.getLogger(ExeScriptBash.class);

        logger.info("Estoy dentro de la función de callback asignada 'GitHubRepoCloner.handleCallback'");

        String outputCode = "";

        String[] urlParts = taskContent.split("/");
        String repoName = urlParts[urlParts.length - 1];
        String destDir = EnvParameters.getParameter("destDir");
        String targetDir = destDir + "/" + messageId + "/" + repoName;

        String outputFile = destDir + "/" + messageId + "/" + "outputs.txt";

        try {
            File targetDirectory = new File(targetDir);
            if (!targetDirectory.exists()) {
                targetDirectory.mkdirs();
            }
            
            ProcessBuilder processBuilder = new ProcessBuilder("git", "clone", taskContent, targetDir).redirectOutput(new File(outputFile));;
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();
            int exitCode = process.waitFor();

            if (exitCode == 0) {
                outputCode = "0";
                logger.info("El repositorio se ha clonado correctamente.");
            } else {
                outputCode = "1";
                logger.info("No se ha conseguido clonar el repositorio.");
            }

        } catch (Exception e) {
            logger.error("Ha habido un error en la ejecución del 'git clone'.");
            e.printStackTrace();
        }

        String resultsPath = EnvParameters.getParameter("destDir");
        resultsPath += "/" + messageId + "/" + "outputs.txt";

        JsonGenerator jsonGenerator = new JsonGenerator();
		jsonGenerator.generateJson(resultsPath, outputCode, messageId);
    }
}