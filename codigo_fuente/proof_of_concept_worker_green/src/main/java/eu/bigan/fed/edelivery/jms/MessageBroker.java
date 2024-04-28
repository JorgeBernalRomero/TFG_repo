package eu.bigan.fed.edelivery.jms;

import java.io.File;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;
import eu.bigan.fed.edelivery.Sender;
import eu.bigan.fed.edelivery.message.BiganFedListener;
import eu.bigan.fed.edelivery.utils.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

        final Logger logger = LogManager.getLogger(MessageBroker.class);

	    try {
            //System.out.println("Beggining of the received message.");
            //System.out.println(msg);
            //System.out.println("End of the received message.");

            logger.info("Recibo correctamente el mensaje en el onMessage.");

            MapMessage m = (MapMessage) msg;
            
            if (msg instanceof MapMessage) {

            	//System.out.println(msg.getJMSMessageID());
                //System.out.println(m.getBytes("payload_1"));
                //System.out.println(new String(m.getBytes("payload_1"),"UTF-8"));

                String fromNodeID = m.getStringProperty("fromPartyId");    
                //System.out.println(fromNodeID);
                
                String messageId = m.getStringProperty("conversationId");
                //System.out.println(messageId);

                String destDir = EnvParameters.getParameter("destDir");

                String saveDir = destDir + "/" + messageId;

                //crer un nuevo saveDir
                File finalDir = new File(saveDir);
				if (!finalDir.exists()) {
        			finalDir.mkdirs();
                }

                File destinationFile = new File(finalDir, "payload_1");
				FileManager.writeBytesToFile(destinationFile, m.getBytes("payload_1"));
        		//System.out.println("File saved to: " + destinationFile.getAbsolutePath());
                logger.info("El fichero se ha guardado correctamente.");


                ReaderJsonContent reader = new ReaderJsonContent();
                reader.readJsonContentFromFile(destinationFile.getAbsolutePath());

                String taskContent = reader.getTaskContent();
                String workerTask = reader.getWorkerTask();

                //System.out.println(taskContent);
                //System.out.println(workerTask);

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
                //System.out.println("No Message Found!");
                logger.error("Mensaje inválido.");
            }

            //System.out.println("fin");
        } catch (Exception e) {
            logger.error("No ha sido posible gestionar el mensaje recibido en el onMessage.");
            e.printStackTrace();
        }		

	}

}