package eu.bigan.fed.edelivery.jms;

import javax.jms.Session;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Queue;
import eu.bigan.fed.edelivery.message.MessageRegistry;
import eu.bigan.fed.edelivery.utils.EnvParameters;

/**
 * Texto sobre lo que hace esta clase.
 * @author jorgebernalromero
 *
 */
public class ConsumerBuilder {

	/**
	 * 
	 * @param session
	 * @param messageRegistry
	 * @return
	 */
	public MessageConsumer createConsumer(Session session, MessageRegistry messageRegistry) {
		
		final Logger logger = LogManager.getLogger(ConsumerBuilder.class);
		
		MessageConsumer consumer = null;
		
		try {
			Queue queue = session.createQueue(EnvParameters.getParameter("outQueue"));
			consumer = session.createConsumer(queue, "", true);
			consumer.setMessageListener((MessageListener) new MessageBroker(messageRegistry));
			
			logger.info("El consumer se ha creado correctamente.");

		} catch (JMSException e) {
			logger.error("Ha habido un problema en la creación del consumer.");
			e.printStackTrace();
		}
		
		return consumer;
	}
}
