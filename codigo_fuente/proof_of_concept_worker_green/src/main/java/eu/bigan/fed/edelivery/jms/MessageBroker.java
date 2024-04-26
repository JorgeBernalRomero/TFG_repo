package eu.bigan.fed.edelivery.jms;

import java.io.File;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;
import eu.bigan.fed.edelivery.Sender;
import eu.bigan.fed.edelivery.message.BiganFedListener;
import eu.bigan.fed.edelivery.utils.*;

/**
 * Texto sobre lo que hace esta clase.
 * @author jorgebernalromero
 *
 */
public class MessageBroker implements MessageListener {

    Session session;
    
    /**
     * 
     */
    public MessageBroker(Session session) {
		this.session =  session;
	}

	@Override
    /**
     * 
     */
	public void onMessage(Message msg) {

	    try {
            System.out.println("Beggining of the received message.");
            System.out.println(msg);
            System.out.println("End of the received message.");

            MapMessage m = (MapMessage) msg;
            
            if (msg instanceof MapMessage) {

            	System.out.println(msg.getJMSMessageID());
                System.out.println(m.getBytes("payload_1"));
                System.out.println(new String(m.getBytes("payload_1"),"UTF-8"));

                String fromNodeID = m.getStringProperty("fromPartyId");    
                System.out.println(fromNodeID);
                
                String messageId = m.getStringProperty("conversationId");
                System.out.println(messageId);

                String destDir = EnvParameters.getParameter("destDir");

                String saveDir = destDir + "/" + messageId;

                //crer un nuevo saveDir
                File finalDir = new File(saveDir);
				if (!finalDir.exists()) {
        			finalDir.mkdirs();
                }

                File destinationFile = new File(finalDir, "payload_1");
				FileManager.writeBytesToFile(destinationFile, m.getBytes("payload_1"));
        		System.out.println("File saved to: " + destinationFile.getAbsolutePath());

                ReaderJsonContent reader = new ReaderJsonContent();
                reader.readJsonContentFromFile(destinationFile.getAbsolutePath());

                String taskContent = reader.getTaskContent();
                String workerTask = reader.getWorkerTask();

                System.out.println(taskContent);
                System.out.println(workerTask);

                //ahora hay que llamar a la función de callback que me hace lo que yo quiero
                String callbackClassName = workerTask;
		        BiganFedListener callback = null;
		      
                try {
                    Class<?> callbackClass = Class.forName(callbackClassName);
                    callback = (BiganFedListener) callbackClass.newInstance();
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }

                callback.handleCallback(taskContent, messageId);

                //Una vez termina la función de callback, paso a enviar los resultados
                Sender sender = new Sender(session);
	            sender.send(fromNodeID, messageId);
                
            } else{
                String payload = "No Message Found!";
                System.out.println(payload);
            }

            System.out.println("fin");
        } catch (Exception e) {
            e.printStackTrace();
        }		

	}

}