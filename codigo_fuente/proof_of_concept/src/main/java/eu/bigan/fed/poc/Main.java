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
      MessageRegistry addingMessage = new MessageRegistry();
      
      
      if(!processList.isEmpty()) {

		  // Print the processList
		  for (int i = 0; i < 1 ; i++) { //processList.size() en vez de 1
			  String[] process = processList.get(i);
		      System.out.println("processList[" + i + "] = (" + process[0] + ", " + process[1] + ", " + process[2] + ", " + process[3] + ")");
		      
		      String destNode = process[0];
		      String messageId = process[1];
		      String sendingFile = process[2];
		      String callback = process[3];
		      GetTimestamp getTimeStamp = new GetTimestamp();
		      String timestamp = getTimeStamp.gettingTimeStamp();
		      int status = 0;
		      
		      //callback tiene que ser de tipo BiganFedListener...
		      addingMessage.addMessage(messageId, destNode, callback, timestamp, status);
		      
		      sender.sending(session, producer, destNode, messageId, sendingFile);
		      
		      
		      
		      MapMessage messageMap = null;
		      
		  }
		  
		  
		  
		  /* String callbackClassName = (String) process[3];
		  
		  System.out.println(callbackClassName); //esto contiene SaveToFiles, habría que meterlo abajo en vez de hardcodearlo

		  
		  //ESTO FUNCIONA CORRECTAMENTE Y ME PERMITE LLEGAR A LA FUNCIÓN DE HANDLING CALLBACK (EL PROBLEMA ES QUE NO DEBERÍA HARDCODEAR EL "SaveToFiles")
		  try {
		      Class<SaveToFiles> callbackClass = SaveToFiles.class;
		      BiganFedListener callback = callbackClass.newInstance();
		      callback.handleCallback(messageMap);
		
		  } 
		  catch (Exception e){
		          e.printStackTrace();
		  }*/*/
    	  
    
		  
		 
      }

  }
}
