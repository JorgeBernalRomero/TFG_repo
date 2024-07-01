package eu.bigan.fed.poc;

import java.util.List;
import javax.jms.Session;
import eu.bigan.fed.edelivery.jms.*;
import eu.bigan.fed.edelivery.message.*;
import eu.bigan.fed.edelivery.ops.Synchronizer;
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
	      
				String messageId = process[1];
				
				if (!messageId.equalsIgnoreCase("synchronize")) {
					
					String destNode = process[0];
					
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
		      
					MessageMetadata actualMessage = messageRegistry.getMessageFromListById(messageId);
		      
					JsonGenerator jsonGenerator = new JsonGenerator();
					String sendingPayload = jsonGenerator.generateJson(workerTask, taskContent, messageId);
				
					
					Thread timeoutThread = new Thread(() -> {
						Timeout timeoutFunc = new Timeout();
						timeoutFunc.completeTimeout(timeout, actualMessage);
					});
	
					Thread messageThread = new Thread(() -> {
						sender.send(destNode, messageId, sendingPayload);
					});
	
					timeoutThread.start();
					messageThread.start();
				}
				else {
					
					String[] processPrev2 = processList.get(i-2);
					String messageIdPrev2 = processPrev2[1];
					String[] processPrev1 = processList.get(i-1);
					String messageIdPrev1 = processPrev1[1];
					
					MessageMetadata prev2Message = messageRegistry.getMessageFromListById(messageIdPrev2);
					MessageMetadata prev1Message = messageRegistry.getMessageFromListById(messageIdPrev1);
					
					Synchronizer synchronizer = new Synchronizer();
					synchronizer.Synchronize(prev2Message, prev1Message);
				}
			}
				
		  
			//Las siguientes líneas sirven para mostrar todos los mensajes que hay en la lista, luego las tendré que quitar
			List<MessageMetadata> fullList = messageRegistry.getAllMessages();
		}
	}
}
