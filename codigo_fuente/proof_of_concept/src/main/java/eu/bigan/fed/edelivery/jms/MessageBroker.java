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
                
                //me voy a la lusta por id, cojo el callback, y al callback que recoja
                
         
                
                //SE ME OCURRE COGER EL CAMPO SET_CORRELATION_ID DE JMS Y USARLO PARA BUSCAR POR ESO EN LA LISTA
                
                
                //manejar el payload, qué hago con él???
                
                
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
