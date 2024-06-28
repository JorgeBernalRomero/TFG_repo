package testDelivery;

import java.io.*;
import javax.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;


public class send_and_server {
	public static class FileManager {
		public static byte[] readFileAsBytes(File file) throws IOException {
			try (RandomAccessFile accessFile = new RandomAccessFile(file, "r")){
				byte[] bytes = new byte[(int) accessFile.length()];
				accessFile.readFully(bytes);
				return bytes;
			}
		}

		public void writeFileAsBytes(byte[] bytes, String fileName) throws IOException {
			File file = new File(fileName);
			try (RandomAccessFile accessFile = new RandomAccessFile(file, "w")){
				accessFile.write(bytes);
			}
		}

		public static void writeBytesToFile(File receivedFile, byte[] fileBytes) throws IOException {
			try(FileOutputStream fos = new FileOutputStream(receivedFile)){
				fos.write(fileBytes);
			}
		}
	}



	public static void listening (Session session){
		try{
			MessageConsumer consumer = session.createConsumer(session.createQueue("domibus.backend.jms.outQueue"));
			Message msg = consumer.receive();
		    System.out.println(msg);

			MapMessage m = (MapMessage) msg;
			if (msg instanceof MapMessage) {
				System.out.println(msg.getJMSMessageID());
				System.out.println(m.getBytes("payload_1"));
				System.out.println(new String(m.getBytes("payload_1"),"UTF-8"));

				String saveDirectory = "/root/TFG/recepcionResults";
				File saveDir = new File(saveDirectory);
				if (!saveDir.exists()) {
        			saveDir.mkdirs();
        		}

				File destinationFile = new File(saveDir, "payload_1");
				FileManager.writeBytesToFile(destinationFile, m.getBytes("payload_1"));
        		System.out.println("File saved to: " + destinationFile.getAbsolutePath());


        		System.out.println("Got a message: ");
			    System.out.println(msg);
			 }

			else{
			    String payload = "No Message Found!";
			    System.out.println(payload);
			}

			System.out.println("fin");

			consumer.close();

		}
		catch (Exception e){
			e.printStackTrace();
		}
	}



    public static void sending(Session session){
		try{

			MessageProducer producer = null;
			Destination destination = session.createQueue("domibus.backend.jms.inQueue");
			producer = session.createProducer(destination);

			producer.setDeliveryMode(DeliveryMode.PERSISTENT);
			MapMessage messageMap = session.createMapMessage();

			messageMap.setStringProperty("messageType", "submitMessage");
			messageMap.setStringProperty("service","bdx:noprocess");
			messageMap.setStringProperty("serviceType","tc1");
			messageMap.setStringProperty("action", "TC1Leg1");
			messageMap.setStringProperty("fromPartyId", "domibus-blue");
			messageMap.setStringProperty("fromPartyType", "urn:oasis:names:tc:ebcore:partyid-type:unregistered");
			messageMap.setStringProperty("toPartyId", "domibus-pink");
			messageMap.setStringProperty("toPartyType", "urn:oasis:names:tc:ebcore:partyid-type:unregistered");
			messageMap.setStringProperty("fromRole", "http://docs.oasis-open.org/ebxml-msg/ebms/v3.0/ns/core/200704/initiator");
			messageMap.setStringProperty("toRole", "http://docs.oasis-open.org/ebxml-msg/ebms/v3.0/ns/core/200704/responder");
			messageMap.setStringProperty("originalSender", "urn:oasis:names:tc:ebcore:partyid-type:unregistered:C1");
			messageMap.setStringProperty("finalRecipient", "urn:oasis:names:tc:ebcore:partyid-type:unregistered:C4");
			messageMap.setStringProperty("protocol", "AS4");
			messageMap.setJMSCorrelationID("12345");
			messageMap.setStringProperty("totalNumberOfPayloads", "1");

			messageMap.setStringProperty("payload_1_mimeContentId", "cid:message");
			messageMap.setStringProperty("payload_1_mimeType", "text/xml");
			messageMap.setStringProperty("payload_1_description", "message");

            File file = new File("/root/TFG/envioScripts/script.sh");
			messageMap.setBytes("payload_1", FileManager.readFileAsBytes(file));

			producer.send(messageMap);
			System.out.println(messageMap);
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}




	public static void main(String[] args){
		try{
			ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://1.44.4.88:61616");
			Connection connection = null;

			connection = connectionFactory.createConnection("admin", "123456");
			connection.start();
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			sending(session);

			listening(session);

			connection.close();
			System.out.println("Connection ends");
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
}
