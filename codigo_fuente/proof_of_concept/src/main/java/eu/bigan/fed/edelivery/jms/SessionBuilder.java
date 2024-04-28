package eu.bigan.fed.edelivery.jms;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Session;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import eu.bigan.fed.edelivery.utils.EnvParameters;

/**
 * Texto sobre lo que hace esta clase.
 * @author jorgebernalromero
 *
 */
public class SessionBuilder {
	
	/**
	 * 
	 * @return
	 */
	public Session createSession() {
		
		final Logger logger = LogManager.getLogger(SessionBuilder.class);
		
		Session session = null;
		
		try {
			String url = EnvParameters.getParameter("activeMQ_url");
			String user = EnvParameters.getParameter("activeMQ_user");
			String pass = EnvParameters.getParameter("activeMQ_pass");
			
			ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
			
			Connection connection = connectionFactory.createConnection(user, pass);
			
			connection.start();
			
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			
			//System.out.println("he creado la sesión");
			
			logger.info("La sesión se ha creado correctamente.");
			
		} catch (JMSException e) {
			logger.error("Ha habido un problema en la creación de la sesión.");
			e.printStackTrace();
		}
		
		return session;
	}

}
