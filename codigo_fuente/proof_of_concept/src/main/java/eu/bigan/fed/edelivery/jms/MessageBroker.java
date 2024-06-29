package eu.bigan.fed.edelivery.jms;

import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import eu.bigan.fed.edelivery.message.BiganFedListener;
import eu.bigan.fed.edelivery.message.MessageMetadata;
import eu.bigan.fed.edelivery.message.MessageRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Texto sobre lo que hace esta clase...
 * @author jorgebernalromero
 *
 */
public class MessageBroker implements MessageListener {
	
	MessageRegistry messageRegistry;
	
	/**
	 * 
	 * @param messageRegistry
	 */
	public MessageBroker(MessageRegistry messageRegistry) {
		this.messageRegistry =  messageRegistry;
	}

	@Override
	/**
	 * @param msg
	 * @return
	 */
	public void onMessage(Message msg) {
		
		final Logger logger = LogManager.getLogger(MessageBroker.class);

	    try {
	    	
	    	logger.info("Recibo correctamente el mensaje en el onMessage.");

            MapMessage m = (MapMessage) msg;
            
            if (msg instanceof MapMessage) {

                String fromNodeID = m.getStringProperty("fromPartyId");
                
                String messageId = m.getStringProperty("conversationId");
                
				MessageMetadata coincidence = messageRegistry.getMessageFromListById(messageId);
				
				int status = coincidence.getStatus();
                
				if (coincidence != null) {
					logger.info("El mensaje ha sido encontrado en la lista de mensajes.");
					if(status !=1) {
						BiganFedListener callback = coincidence.getCallback();
						callback.handleCallback(m);
					}
					else {
						logger.info("Timeout excedido para el mensaje a procesar. No se llama a la función de callback.");
					}
				} else {
					logger.error("El mensaje no ha sido encontrado en la lista de mensajes.");
				}
 
                
            } else{
            	logger.error("Mensaje inválido.");
            }

            
        } catch (Exception e) {
        	logger.error("No ha sido posible gestionar el mensaje recibido en el onMessage.");
            e.printStackTrace();
        }		

	}
}