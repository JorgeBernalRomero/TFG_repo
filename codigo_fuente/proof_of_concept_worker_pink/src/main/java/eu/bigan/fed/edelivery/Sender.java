package eu.bigan.fed.edelivery;

import java.io.File;
import javax.jms.*;
import eu.bigan.fed.edelivery.jms.SenderBuilder;
import eu.bigan.fed.edelivery.utils.EnvParameters;
import eu.bigan.fed.edelivery.utils.FileManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Texto sobre lo que hace esta clase.
 * @author jorgebernalromero
 *
 */
public class Sender{

    private Session session;
	private MessageProducer producer;

    /**
     * 
     */
	public Sender(Session session){
		this.session = session;
		SenderBuilder senderBuilder = new SenderBuilder();
		this.producer = senderBuilder.createProducer(session);
	}
	
    /**
     * 
     */
	public void send(String destinationNode, String messageId){

        final Logger logger = LogManager.getLogger(Sender.class);

        try{

            MapMessage messageMap = session.createMapMessage();

            messageMap.setStringProperty("messageType", "submitMessage");
            messageMap.setStringProperty("service","bdx:noprocess");

            messageMap.setStringProperty("serviceType","tc1");
            messageMap.setStringProperty("action", "TC1Leg1");

            String nodeName = EnvParameters.getParameter("nodeName");
            messageMap.setStringProperty("fromPartyId", nodeName);
            messageMap.setStringProperty("fromPartyType", "urn:oasis:names:tc:ebcore:partyid-type:unregistered");
            messageMap.setStringProperty("toPartyId", destinationNode);
            messageMap.setStringProperty("toPartyType", "urn:oasis:names:tc:ebcore:partyid-type:unregistered");
            messageMap.setStringProperty("fromRole", "http://docs.oasis-open.org/ebxml-msg/ebms/v3.0/ns/core/200704/initiator");
            messageMap.setStringProperty("toRole", "http://docs.oasis-open.org/ebxml-msg/ebms/v3.0/ns/core/200704/responder");
            messageMap.setStringProperty("originalSender", "urn:oasis:names:tc:ebcore:partyid-type:unregistered:C1");
            messageMap.setStringProperty("finalRecipient", "urn:oasis:names:tc:ebcore:partyid-type:unregistered:C4");
            messageMap.setStringProperty("protocol", "AS4");
            messageMap.setStringProperty("conversationId", messageId);
            messageMap.setStringProperty("totalNumberOfPayloads", "1");

            messageMap.setStringProperty("payload_1_mimeContentId", "cid:message");
            messageMap.setStringProperty("payload_1_mimeType", "text/xml");
            messageMap.setStringProperty("payload_1_description", "message");

            String sendingFile = EnvParameters.getParameter("destDir");
            sendingFile += "/" + messageId + "/" + "returnJson.json";

			File file = new File(sendingFile);

            messageMap.setBytes("payload_1", FileManager.readFileAsBytes(file));

            producer.send(messageMap);

            logger.info("El envío se ha realizado correctamente.");
        }
        catch (Exception e){
            logger.error("No se ha podido realizar el envío.");
            e.printStackTrace();
        }
    }
}