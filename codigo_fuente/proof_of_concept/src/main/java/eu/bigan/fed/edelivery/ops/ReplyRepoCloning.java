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
      
      /*try {
	      MapMessage m = (MapMessage) messageMap;
	      if (messageMap instanceof MapMessage) {
	          //System.out.println(messageMap.getJMSMessageID());
	          //System.out.println(m.getBytes("payload_1"));
	          //System.out.println(new String(m.getBytes("payload_1"),"UTF-8"));
	
	          String fromNodeID = m.getStringProperty("fromPartyId");
	          System.out.println(fromNodeID);
	          
	          String messageId = m.getStringProperty("conversationId");
	          System.out.println(messageId);
	          
	          //ver el contenido del payload (ahora es un .json)
	          
       		}
	
	      else{
	          //System.out.println("No Message Found!");
	    	  logger.error("No se ha conseguido guardar el archivo."); //cambiar
	      }
      } catch (Exception e) {
    	  logger.error("Ha habido un error en la gestión del archivo."); //cambiar
          e.printStackTrace();
      }*/
   }
  
}