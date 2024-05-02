package eu.bigan.fed.edelivery.ops;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import eu.bigan.fed.edelivery.message.BiganFedListener;
import eu.bigan.fed.edelivery.utils.EnvParameters;

public class GitHubRepoCloner implements BiganFedListener {

    public void handleCallback(String taskContent, String messageId) {
        final Logger logger = LogManager.getLogger(ExeScriptBash.class);

        //System.out.println("estoy dentro del handling callback y me ha llegado el siguiente taskContent:");
        logger.info("Estoy dentro de la función de callback asignada 'GitHubRepoCloner.handleCallback'");

        //System.out.println(taskContent);

        String destDir = EnvParameters.getParameter("destDir");

        //Converting taskContent into an executable file
        /*String filePath = destDir + "/" + messageId + "/" + "taskContentFile.sh";
        byte[] contentBytes = taskContent.getBytes();
        File file = new File(filePath);
        try{
            FileManager.writeBytesToFile(file, contentBytes);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            ProcessBuilder processBuilder = new ProcessBuilder("/bin/bash", filePath);
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();
            int exitCode = process.waitFor();
            if(exitCode == 0) {
                //System.out.println("exe correct");
                logger.info("Correcta ejecución del script.");
            }
            else {
                //System.out.println("exe incorrect");
                logger.info("Incorrecta ejecución del script.");
            }
        } catch (Exception e) {
            logger.error("No se ha conseguido ejecutar el script.");
            e.printStackTrace();
        }

        //LO PONGO PARA COMPROBAR QUE TARDE MÁS UNA TAREA Y QUE EL COORDINADOR SI PASA DETERMINADO TIEMPO LA DESECHE
        try {
			TimeUnit.SECONDS.sleep(5); //si pongo 20 no llegan (coordinador está a 15) si pongo 5 si llegan
            logger.info("Se ha producido un sleep de 5 segundos.");
		} catch (InterruptedException e) {
            logger.error("Ha habido un error en el sleep de 5 segundos.");
			e.printStackTrace();
		}*/

        //System.out.println("termina funcion de callback");
    }
}
