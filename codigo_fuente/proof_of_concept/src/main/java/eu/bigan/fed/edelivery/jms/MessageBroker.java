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
	    	
	    	//System.out.println("Beggining of the received message.");
            //System.out.println(msg);
            //System.out.println("End of the received message.");
	    	
	    	logger.info("Recibo correctamente el mensaje en el onMessage.");

            MapMessage m = (MapMessage) msg;
            
            if (msg instanceof MapMessage) {

            	//System.out.println(msg.getJMSMessageID());
                //System.out.println(m.getBytes("payload_1"));
                //System.out.println(new String(m.getBytes("payload_1"),"UTF-8"));

                String fromNodeID = m.getStringProperty("fromPartyId");
                //System.out.println(fromNodeID);
                
                String messageId = m.getStringProperty("conversationId");
                //System.out.println(messageId);
                
				MessageMetadata coincidence = messageRegistry.getMessageFromListById(messageId);
				
				int status = coincidence.getStatus();
                
				if (coincidence != null) {
					logger.info("El mensaje ha sido encontrado en la lista de mensajes.");
					if(status !=1) {
						BiganFedListener callback = coincidence.getCallback();
						callback.handleCallback(m);
					}
					else {
						//System.out.println("Timeout excedido para mensaje " + messageId + ". No se llama a la función de callback.");
						logger.info("Timeout excedido para el mensaje a procesar. No se llama a la función de callback.");
					}
				} else {
					//System.out.println("Message with id " + messageId + " not found in registry");
					logger.error("El mensaje no ha sido encontrado en la lista de mensajes.");
				}
 
                
            } else{
                //System.out.println("No Message Found!);
            	logger.error("Mensaje inválido.");
            }

            //System.out.println("fin");
            
        } catch (Exception e) {
        	logger.error("No ha sido posible gestionar el mensaje recibido en el onMessage.");
            e.printStackTrace();
        }		

	}
}