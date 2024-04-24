package eu.bigan.fed.poc;

import javax.jms.MessageProducer;
import javax.jms.Session;
import eu.bigan.fed.edelivery.jms.*;


public class Main {

  public static void main(String[] args) {
	System.out.println("hola estoy en el main de los workers");

	SessionBuilder sessionBuilder = new SessionBuilder();
	Session session = sessionBuilder.createSession();

	SenderBuilder senderBuilder = new SenderBuilder();
	MessageProducer producer = senderBuilder.createProducer(session);
	
	ConsumerBuilder consumerBuilder = new ConsumerBuilder();
	consumerBuilder.createConsumer(session, producer);

   }
}
