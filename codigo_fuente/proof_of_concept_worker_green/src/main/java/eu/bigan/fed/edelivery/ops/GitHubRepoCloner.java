package eu.bigan.fed.edelivery.ops;

import java.io.File;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import eu.bigan.fed.edelivery.message.BiganFedListener;
import eu.bigan.fed.edelivery.utils.EnvParameters;
import eu.bigan.fed.edelivery.utils.FileManager;


public class GitHubRepoCloner implements BiganFedListener {

    public void handleCallback(String taskContent, String messageId) {
        final Logger logger = LogManager.getLogger(ExeScriptBash.class);

        //System.out.println("estoy dentro del handling callback y me ha llegado el siguiente taskContent:");
        logger.info("Estoy dentro de la función de callback asignada 'GitHubRepoCloner.handleCallback'");

        //aquí messageID va a ser el task_green
        //taskContent = "git@github.com:JorgeBernalRomero/proof_of_concept_eDelivery_repo.git"

        //montar una ejecución de un git clone de "git@github.com:JorgeBernalRomero/proof_of_concept_eDelivery_repo.git"
        //guardando los results en un string para montar a posteirori el json de vueta


        //PENDIENTE IMPLEMENTACIÓN


        //System.out.println("termina funcion de callback");
    }
}
