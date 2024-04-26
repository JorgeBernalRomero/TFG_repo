package eu.bigan.fed.edelivery.jms;

import javax.jms.Session;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import eu.bigan.fed.edelivery.utils.EnvParameters;

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
		MessageProducer producer = null;
		
		try {
			Queue queue = session.createQueue(EnvParameters.getParameter("inQueue"));
			producer = session.createProducer(queue);
			producer.setDeliveryMode(DeliveryMode.PERSISTENT);
			
		} catch (JMSException e) {
			e.printStackTrace();
		}
		
		return producer;
	}
}