package eu.bigan.fed.poc;

import javax.jms.Session;
import eu.bigan.fed.edelivery.jms.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Texto sobre lo que hace esta clase.
 * @author jorgebernalromero
 *
 */
public class Main {

	/**
	 * 
	 */
	public static void main(String[] args) {

		final Logger logger = LogManager.getLogger(Main.class);
		
		logger.info("Comienza el worker.");

		SessionBuilder sessionBuilder = new SessionBuilder();
		Session session = sessionBuilder.createSession();
		
		ConsumerBuilder consumerBuilder = new ConsumerBuilder();
		consumerBuilder.createConsumer(session);

   	}
}