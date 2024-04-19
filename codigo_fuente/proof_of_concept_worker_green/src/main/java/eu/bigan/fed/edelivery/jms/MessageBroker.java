package eu.bigan.fed.edelivery.jms;

import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import eu.bigan.fed.edelivery.message.BiganFedListener;

public class MessageBroker implements MessageListener {

	@Override
	public void onMessage(Message msg) {

	    try {
            System.out.println("Beggining of the received message.");
            System.out.println(msg);
            System.out.println("End of the received message.");

            MapMessage m = (MapMessage) msg;
            
            if (msg instanceof MapMessage) {

            	System.out.println(msg.getJMSMessageID());
                System.out.println(m.getBytes("payload_1"));
                System.out.println(new String(m.getBytes("payload_1"),"UTF-8"));

                String fromNodeID = m.getStringProperty("fromPartyId");
                System.out.println(fromNodeID);
                
                String messageId = m.getStringProperty("conversationId");
                System.out.println(messageId);
                
                //busco en la lista de mensajes por conversationId, y llamo al callback con el payload completo
				/*ManageMetadata coincidence = messageRegistry.getMessageFromListById(messageId);
                
				if (coincidence != null) {
					BiganFedListener callback = coincidence.getCallback();
					callback.handleCallback(m);
				} else {
					System.out.println("Message with id " + messageId + " not found in registry");
				}*/
 
                
            } else{
                String payload = "No Message Found!";
                System.out.println(payload);
            }

            System.out.println("fin");
        } catch (Exception e) {
            e.printStackTrace();
        }		

	}

}