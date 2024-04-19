package eu.bigan.fed.poc;

import javax.jms.MessageProducer;
import javax.jms.Session;
import eu.bigan.fed.edelivery.jms.*;


public class Main {

  public static void main(String[] args) {
	System.out.println("hola estoy en el main de los workers");
	  
	SessionBuilder sessionBuilder = new SessionBuilder();
	Session session = sessionBuilder.createSession();
	
	ConsumerBuilder consumerBuilder = new ConsumerBuilder();
	consumerBuilder.createConsumer(session);
	
	SenderBuilder senderBuilder = new SenderBuilder();
	MessageProducer producer = senderBuilder.createProducer(session);
	
	Sender sender = new Sender();


	//A partir de aquí tengo que empezar a cambiar el código para los workers


   }
}
