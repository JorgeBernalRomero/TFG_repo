package eu.bigan.fed.poc;

import javax.jms.MessageProducer;
import javax.jms.Session;
import eu.bigan.fed.edelivery.jms.*;
import eu.bigan.fed.edelivery.message.ReturnMetadata;
import eu.bigan.fed.edelivery.utils.StatusManager;


public class Main {

  public static void main(String[] args) {
	System.out.println("hola estoy en el main de los workers");

	int status = StatusManager.getInstance();

	ReturnMetadata returnMetadata = ReturnMetadata.getInstance();

	  
	SessionBuilder sessionBuilder = new SessionBuilder();
	Session session = sessionBuilder.createSession();
	
	ConsumerBuilder consumerBuilder = new ConsumerBuilder();
	consumerBuilder.createConsumer(session);
	
	//espera activa hasta que se realice correctamente la función de callback
	while(StatusManager.getStatus() !=1){
		
	}

	SenderBuilder senderBuilder = new SenderBuilder();
	MessageProducer producer = senderBuilder.createProducer(session);
	Sender sender = new Sender();

	String messageId = ReturnMetadata.getMessageId();
	String destinationNode = ReturnMetadata.getDestNode();	
	
	sender.sending(session, producer, destinationNode, messageId);

	System.out.println("termina el worker green");


   }
}
