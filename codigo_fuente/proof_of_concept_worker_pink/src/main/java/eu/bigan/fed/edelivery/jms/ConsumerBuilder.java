package eu.bigan.fed.edelivery.jms;

import javax.jms.Session;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import eu.bigan.fed.edelivery.utils.EnvParameters;

public class ConsumerBuilder {

	public MessageConsumer createConsumer(Session session, MessageProducer producer) {
		MessageConsumer consumer = null;
		
		try {
			Queue queue = session.createQueue(EnvParameters.getParameter("outQueue"));
			consumer = session.createConsumer(queue, "", true);
			consumer.setMessageListener((MessageListener) new MessageBroker(session, producer));
			
			System.out.println("acabo de crear el consumer");

		} catch (JMSException e) {
			e.printStackTrace();
		}
		
		return consumer;
	}
}
