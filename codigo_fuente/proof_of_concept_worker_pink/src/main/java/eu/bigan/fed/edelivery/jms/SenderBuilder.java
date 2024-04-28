package eu.bigan.fed.edelivery.jms;

import javax.jms.Session;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import eu.bigan.fed.edelivery.utils.EnvParameters;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Texto sobre lo que hace esta clase.
 * @author jorgebernalromero
 *
 */
public class SenderBuilder {
	
	/**
	 * 
	 */
	public MessageProducer createProducer(Session session) {

		final Logger logger = LogManager.getLogger(SenderBuilder.class);

		MessageProducer producer = null;
		
		try {
			Queue queue = session.createQueue(EnvParameters.getParameter("inQueue"));
			producer = session.createProducer(queue);
			producer.setDeliveryMode(DeliveryMode.PERSISTENT);
			logger.info("Se ha creado el producer correctamente.");
			
		} catch (JMSException e) {
			logger.error("Ha habido un error en la creación del producer.");
			e.printStackTrace();
		}
		
		return producer;
	}
}