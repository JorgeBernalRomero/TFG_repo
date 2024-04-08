package eu.bigan.fed.edelivery.jms;

import java.io.File;
import javax.jms.*;

import eu.bigan.fed.edelivery.utils.FileManager;


public class Sender{
	public static void sending(Session session, String destinationNode){
        try{
            System.out.println("I'm in the sending now!");

            MapMessage messageMap = session.createMapMessage();

            messageMap.setStringProperty("messageType", "submitMessage");
            messageMap.setStringProperty("service","bdx:noprocess");

            messageMap.setStringProperty("serviceType","tc1");
            messageMap.setStringProperty("action", "TC1Leg1");
            messageMap.setStringProperty("fromPartyId", "domibus-blue"); //nodo inicial
            messageMap.setStringProperty("fromPartyType", "urn:oasis:names:tc:ebcore:partyid-type:unregistered");
            messageMap.setStringProperty("toPartyId", destinationNode); //nodo destino
            messageMap.setStringProperty("toPartyType", "urn:oasis:names:tc:ebcore:partyid-type:unregistered");
            messageMap.setStringProperty("fromRole", "http://docs.oasis-open.org/ebxml-msg/ebms/v3.0/ns/core/200704/initiator");
            messageMap.setStringProperty("toRole", "http://docs.oasis-open.org/ebxml-msg/ebms/v3.0/ns/core/200704/responder");
            messageMap.setStringProperty("originalSender", "urn:oasis:names:tc:ebcore:partyid-type:unregistered:C1");
            messageMap.setStringProperty("finalRecipient", "urn:oasis:names:tc:ebcore:partyid-type:unregistered:C4");
            messageMap.setStringProperty("protocol", "AS4");
            messageMap.setJMSCorrelationID("12345");
            messageMap.setStringProperty("totalNumberOfPayloads", "1");

            //messageMap.setStringProperty("payload_1_mimeContentId", "cid:file-attached");
            messageMap.setStringProperty("payload_1_mimeContentId", "cid:message");
            messageMap.setStringProperty("payload_1_mimeType", "text/xml");
            messageMap.setStringProperty("payload_1_description", "message");

			File file = null;

            switch(destinationNode) {
                case "domibus-green":
                    file = new File("/Users/jorgebernalromero/Documents/INGENIERIA_INFORMATICA/Cuarto_uni/Segundo_cuatri/TFG/ficheros_comunicacion/blueNode/envioScripts/script_green.sh"); //directorio puede modificarse o por parámetro???
                    break;
                case "domibus-pink":
                    file = new File("/Users/jorgebernalromero/Documents/INGENIERIA_INFORMATICA/Cuarto_uni/Segundo_cuatri/TFG/ficheros_comunicacion/blueNode/envioScripts/script_pink.sh"); //directorio puede modificarse o por parámetro???
                    break;
            }

            messageMap.setBytes("payload_1", FileManager.readFileAsBytes(file));

            producer.send(messageMap); //habrá que llamar al SenderBuilder en algún momento para crear un productor y enviar el mensaje desde el mismo
           


            System.out.println("Sending message content is the following!");
            System.out.println(messageMap);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}