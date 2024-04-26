package eu.bigan.fed.poc;

import javax.jms.MessageProducer;
import javax.jms.Session;
import eu.bigan.fed.edelivery.jms.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Main {

  public static void main(String[] args) {

	final Logger logger = LogManager.getLogger(Main.class);
	  
  	logger.info("Mensaje de información sacado por el logger.");
    logger.error("Mensaje de error sacado por el logger.");
	
	System.out.println("hola estoy en el main de los workers");

	SessionBuilder sessionBuilder = new SessionBuilder();
	Session session = sessionBuilder.createSession();

	SenderBuilder senderBuilder = new SenderBuilder();
	MessageProducer producer = senderBuilder.createProducer(session);
	
	ConsumerBuilder consumerBuilder = new ConsumerBuilder();
	consumerBuilder.createConsumer(session, producer);

   }
}