package eu.bigan.fed.poc;

import java.util.List;
import javax.jms.Session;
import eu.bigan.fed.edelivery.jms.*;
import eu.bigan.fed.edelivery.message.*;
import eu.bigan.fed.edelivery.ops.Timeout;
import eu.bigan.fed.edelivery.utils.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import eu.bigan.fed.edelivery.*;

/**
 * Texto sobre lo que hace esta clase.
 * @author jorgebernalromero
 *
 */
public class Main {
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		final Logger logger = LogManager.getLogger(Main.class);
			  
		//logger.info("Mensaje de información sacado por el logger (ejemplo MAIN).");
		//logger.error("Mensaje de error sacado por el logger (ejemplo MAIN).");
		
		//System.out.println("hola estoy en el main del coord");
		logger.info("Comienza el coordinador.");
		  
		SessionBuilder sessionBuilder = new SessionBuilder();
		Session session = sessionBuilder.createSession();
		  
		MessageRegistry messageRegistry = MessageRegistry.getInstance();
		  
		ConsumerBuilder consumerBuilder = new ConsumerBuilder();
		consumerBuilder.createConsumer(session, messageRegistry);
  
		Sender sender = new Sender(session);
  
		YamlReader yamlReader = new YamlReader();
		List<String[]> processList = yamlReader.read();

      
		if(!processList.isEmpty()) {
			for (int i = 0; i < processList.size() ; i++) {
				String[] process = processList.get(i);
				
				//este print lo dejo a modo de debugging de momento
				System.out.println("processList[" + i + "] = (" + process[0] + ", " + process[1] + ", " + process[2] + ", " + process[3] + ", " + process[4] + ")");
	      
				String destNode = process[0];
				String messageId = process[1];
				String taskContent = process[2];
				String workerTask = process[3];
		      
				String callbackClassName = process[4];
				BiganFedListener callback = null;
	      
				String timeoutString =  process[5];
				int timeout = Integer.parseInt(timeoutString);
	      
				try {
					Class<?> callbackClass = Class.forName(callbackClassName);
					callback = (BiganFedListener) callbackClass.newInstance();
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
					e.printStackTrace();
				}
	      
				int status = 0;
	      
				MessageMetadata metadata = new MessageMetadata(messageId, destNode, callback, status);
				messageRegistry.addMessage(metadata);
	      
				//recupero el mensaje que acabo de añadir
				MessageMetadata actualMessage = messageRegistry.getMessageFromListById(messageId);
	      
				JsonGenerator jsonGenerator = new JsonGenerator();
				String sendingPayload = jsonGenerator.generateJson(workerTask, taskContent, messageId);
				
				//System.out.println(sendingPayload);
		      
		      
				Thread timeoutThread = new Thread(() -> {
					Timeout timeoutFunc = new Timeout();
					timeoutFunc.completeTimeout(timeout, actualMessage);
				});

				Thread messageThread = new Thread(() -> {
					sender.send(destNode, messageId, sendingPayload);
				});

				// Iniciar los hilos simultáneamente
				timeoutThread.start();
				messageThread.start();
		      
			}
		  
			//Las siguientes líneas sirven para mostrar todos los mensajes que hay en la lista, luego las tendré que quitar
			List<MessageMetadata> fullList = messageRegistry.getAllMessages();
	      
			List<String[]> showingList = messageRegistry.listConversionToString(fullList);
	      
			for (int j = 0; j < showingList.size(); j++) {
				String[] procesos = showingList.get(j);
	    	  
				System.out.println("procesos[" + j + "] = (" + procesos[0] + ", " + procesos[1] + ", " + procesos[2] + ", " + procesos[3] + ", " + procesos[4] + ")");
			}
		}
	}
}
