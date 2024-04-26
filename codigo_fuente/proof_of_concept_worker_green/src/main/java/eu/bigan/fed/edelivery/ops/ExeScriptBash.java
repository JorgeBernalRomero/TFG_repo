package eu.bigan.fed.edelivery.ops;

import java.io.File;
import java.util.concurrent.TimeUnit;
import eu.bigan.fed.edelivery.message.BiganFedListener;
import eu.bigan.fed.edelivery.utils.*;
import eu.bigan.fed.edelivery.message.BiganFedListener;

/**
 * Texto sobre lo que hace esta clase.
 * @author jorgebernalromero
 *
 */
public class ExeScriptBash implements BiganFedListener{
    
    private String taskContent;

    /**
     * 
     */
    public ExeScriptBash() {
        // Optional: Initialize any default values for properties here (if needed)
    }

    /**
     * 
     */
    public ExeScriptBash(String taskContent) {
        this.taskContent = taskContent;
    }

    @Override
    /**
     * 
     */
    public void handleCallback(String taskContent, String messageId) {
        System.out.println("estoy dentro del handling callback y me ha llegado el siguiente taskContent:");

        System.out.println(taskContent);

        String destDir = EnvParameters.getParameter("destDir");

        //Converting taskContent into an executable file
        String filePath = destDir + "/" + messageId + "/" + "taskContentFile.sh";
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
                System.out.println("exe correct");
            }
            else {
                System.out.println("exe incorrect");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //LO PONGO PARA COMPROBAR QUE TARDE MÁS UNA TAREA Y QUE EL COORDINADOR SI PASA DETERMINADO TIEMPO LA DESECHE
        try {
			TimeUnit.SECONDS.sleep(5); //si pongo 20 no llegan (coordinador está a 15) si pongo 5 si llegan
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

        System.out.println("termina funcion de callback");
    }
}
