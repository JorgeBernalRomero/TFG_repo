package eu.bigan.fed.edelivery.ops;

import javax.jms.MapMessage;
import eu.bigan.fed.edelivery.message.BiganFedListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Texto sobre lo que hace esta clase.
 * @author jorgebernalromero
 *
 */
public class ReplyRepoCloning implements BiganFedListener{

  @Override
  /**
   * 
   */
  public void handleCallback(MapMessage messageMap) {
	  
	  final Logger logger = LogManager.getLogger(ReplyRepoCloning.class);
	 
      System.out.println("estoy dentro del handling callback del ReplyRepoCloning");
	  logger.info("Estoy dentro de la función de callback asignada 'ReplyRepoCloning'");
      
      
	  
	  //PENDIENTE IMPLEMENTACIÓN
	  
	  
   }
  
}