package eu.bigan.fed.poc;

import java.security.Timestamp;
import java.util.List;

import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.Session;
import eu.bigan.fed.edelivery.jms.*;
import eu.bigan.fed.edelivery.message.*;
import eu.bigan.fed.edelivery.utils.*;
import eu.bigan.fed.edelivery.ops.*;



public class Main {

  public static void main(String[] args) {
	  
	  SessionBuilder sessionBuilder = new SessionBuilder();
	  Session session = sessionBuilder.createSession();
	  
	  
	  ConsumerBuilder consumerBuilder = new ConsumerBuilder();
	  consumerBuilder.createConsumer(session);
	  
	  SenderBuilder senderBuilder = new SenderBuilder();
	  MessageProducer producer = senderBuilder.createProducer(session);
	  
	  Sender sender = new Sender();
	  
	  YamlReader yamlReader = new YamlReader();
      List<String[]> processList = yamlReader.yamlReading();
      
      //crear la lista de mensajes vacía, debería de ser la estructura MessageRegistry
      
      if(!processList.isEmpty()) {

		  // Print the processList
		  for (int i = 0; i < 1 ; i++) { //processList.size() en vez de 1
			  String[] process = processList.get(i);
		      System.out.println("processList[" + i + "] = (" + process[0] + ", " + process[1] + ", " + process[2] + ", " + process[3] + ")");
		      
		      String destinationNode = process[0];
		      String JMSCorrelationId = process[1];
		      String sendingFile = process[2];
		      
		      //aquí tengo que añadir un nuevo mensaje al array de arrays de mensajes (MessageRegistry)
		      
		      
		      sender.sending(session, producer, destinationNode, JMSCorrelationId, sendingFile);
		      
		      
		      
		      MapMessage messageMap = null;
		      
		  }
    	  
    	  
    	  
    	  
    	//Ahora tengo que añadir a la lista el mensaje que 
    	  /*RandomIdGenerator randomId = new RandomIdGenerator();
    	  int idMessage = randomId.generateRandomId();
    	  
    	  Timestamp timestamp;
    	  
    	  MessageRegistry addingMessage = new MessageRegistry();
    	  //addingMessage.addMessage(idMessage, "domibus-blue", "domibus-green", "saveAsFile()", null, 0); //modificar
    	  
    	  
    	  Sender sender = new Sender();
    	  sender.sending(session, "domibus-green", producer);
    	  
    	  
    	  sender.sending(session, "domibus-pink", producer);*/
		  
    	  
    	  
    	  
    	  
    	  
    	  
    	  
    	  
    	  //GESTIÓN DE CALLBACK LO DEJO PENDIENTE...
    	  
    	  
    	  //Este código sirve para mostrar las componentes de la primera posición del array
		  /*String[] process = processList.get(0);
		  System.out.println("processList[" + 0 + "] = (" + process[0] + ", " + process[1] + ", " + process[2] + ", " + process[3] + ")");
		  
		  String callbackClassName = (String) process[3];
		  
		  System.out.println(callbackClassName); //esto contiene SaveToFiles, habría que meterlo abajo en vez de hardcodearlo

		  
		  //ESTO FUNCIONA CORRECTAMENTE Y ME PERMITE LLEGAR A LA FUNCIÓN DE HANDLING CALLBACK (EL PROBLEMA ES QUE NO DEBERÍA HARDCODEAR EL "SaveToFiles")
		  try {
		      Class<SaveToFiles> callbackClass = SaveToFiles.class;
		      BiganFedListener callback = callbackClass.newInstance();
		      callback.handleCallback(messageMap);
		
		  } 
		  catch (Exception e){
		          e.printStackTrace();
		  }*/
      }

  }
}
