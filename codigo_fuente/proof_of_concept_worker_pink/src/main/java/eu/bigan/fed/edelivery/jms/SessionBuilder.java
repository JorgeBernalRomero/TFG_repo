package eu.bigan.fed.edelivery.jms;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Session;
import org.apache.activemq.ActiveMQConnectionFactory;
import eu.bigan.fed.edelivery.utils.EnvParameters;


public class SessionBuilder {
	
	public Session createSession() {
		
		Session session = null;
		
		try {
			String url = EnvParameters.getParameter("activeMQ_url");
			String user = EnvParameters.getParameter("activeMQ_user");
			String pass = EnvParameters.getParameter("activeMQ_pass");
			
			ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url); //URL del servidor ActiveMQ
			
			Connection connection = connectionFactory.createConnection(user, pass); //username and password of the default JMS broker
			
			connection.start();
			
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			
			System.out.println("he creado la sesión");
			
		} catch (JMSException e) {
			e.printStackTrace();
		}
		
		return session;
	}

}
