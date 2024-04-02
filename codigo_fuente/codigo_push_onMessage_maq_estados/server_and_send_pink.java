package test;

import java.io.*;
import javax.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;


public class server_and_send implements MessageListener{

	static Session session;
	static Connection connection = null;
	static MessageConsumer consumer;


	//Class for files management
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





	public static void starting(){
		try{
			//Connecting to the ActiveMQ connection factory
			ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://1.44.4.92:61616"); //URL del servidor ActiveMQ

			connection = connectionFactory.createConnection("admin", "123456"); //username and password of the default JMS broker
			connection.start();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			consumer = session.createConsumer(session.createQueue("domibus.backend.jms.outQueue"), "", true);
            consumer.setMessageListener((MessageListener) new server_and_send());

			setStatus(0);

		}
		catch (Exception e){
			e.printStackTrace();
		}
	}



	public static void sending(Session session){
		try{
			System.out.println("I'm in the sending now!");

			MessageProducer producer = null;
			Destination destination = session.createQueue("domibus.backend.jms.inQueue");
			producer = session.createProducer(destination);

			producer.setDeliveryMode(DeliveryMode.PERSISTENT);
			MapMessage messageMap = session.createMapMessage();

			messageMap.setStringProperty("messageType", "submitMessage");
			messageMap.setStringProperty("service","bdx:noprocess");
			messageMap.setStringProperty("serviceType","tc1");
			messageMap.setStringProperty("action", "TC1Leg1");
			messageMap.setStringProperty("fromPartyId", "domibus-pink"); //nodo inicial
			messageMap.setStringProperty("fromPartyType", "urn:oasis:names:tc:ebcore:partyid-type:unregistered");
			messageMap.setStringProperty("toPartyId", "domibus-blue"); //nodo destino
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

			//Sending a file from one node to another works (testing script)
			File file = new File("/home/green/TFG/dir_destino_prueba/outScriptPink.txt"); //directorio puede modificarse o por parámetro???
			messageMap.setBytes("payload_1", FileManager.readFileAsBytes(file));

			producer.send(messageMap);

			System.out.println("Sending message content is the following!");
			System.out.println(messageMap);

			setStatus(2);
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}





	@Override
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

				String saveDirectory = "/home/green/TFG/dir_destino_prueba"; //esta parte se podría sustituir pasando el dirDestino por parámetro
				File saveDir = new File(saveDirectory);
				if (!saveDir.exists()) {
        			saveDir.mkdirs();
        		}

				File destinationFile = new File(saveDir, "payload_1");
				FileManager.writeBytesToFile(destinationFile, m.getBytes("payload_1"));
        		System.out.println("File saved to: " + destinationFile.getAbsolutePath());

        		//Código para la ejecución de un script en el servidor
        		try {
        			ProcessBuilder processBuilder = new ProcessBuilder("/bin/bash", "/home/green/TFG/dir_destino_prueba/payload_1"); //El directorio podría pasarse por parámetro
        			processBuilder.redirectErrorStream(true);
        			Process process = processBuilder.start();
        			int exitCode = process.waitFor();

        			if(exitCode == 0) {
        				System.out.println("exe correct");
        			}
        			else {
        				System.out.println("exe incorrect");
        			}
        		} catch (IOException | InterruptedException e) {
        			e.printStackTrace();
        		}
        		System.out.println("Got a message: ");
			    System.out.println(msg);

				setStatus(1);

				sending(session);
			 }

			else{
			    String payload = "No Message Found!";
			    System.out.println(payload);
			}

			System.out.println("fin");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }





	public static void setStatus(int status){
        int statusInterno;
        statusInterno = status;

		if(statusInterno == 2){
            System.out.println("Tasks done, finishing!");
			try{
				consumer.close();
				session.close();
				connection.close();
			}
			catch (Exception e){
				e.printStackTrace();
			}
            System.exit(0);
        }
    }





	//main code
	public static void main(String[] args){

		starting();

	}
}
