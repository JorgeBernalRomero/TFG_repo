package eu.bigan.fed.poc;

import java.security.Timestamp;
import java.util.List;

import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.Session;
import eu.bigan.fed.edelivery.jms.*;
import eu.bigan.fed.edelivery.message.*;
import eu.bigan.fed.edelivery.utils.*;



public class Main {

  public static void main(String[] args) {
	  
	  /*SessionBuilder sessionBuilder = new SessionBuilder();
	  Session session = sessionBuilder.createSession();
	  
	  
	  ConsumerBuilder consumerBuilder = new ConsumerBuilder();
	  consumerBuilder.createConsumer(session);
	  
	  SenderBuilder senderBuilder = new SenderBuilder();
	  MessageProducer producer = senderBuilder.createProducer(session);
	  
	  
	  //Ahora tengo que añadir a la lista el mensaje que 
	  RandomIdGenerator randomId = new RandomIdGenerator();
	  int idMessage = randomId.generateRandomId();
	  
	  Timestamp timestamp;
	  
	  MessageRegistry addingMessage = new MessageRegistry();
	  //addingMessage.addMessage(idMessage, "domibus-blue", "domibus-green", "saveAsFile()", null, 0); //modificar
	  
	  
	  Sender sender = new Sender();
	  sender.sending(session, "domibus-green", producer);
	  
	  
	  sender.sending(session, "domibus-pink", producer);*/
	  
	  MapMessage messageMap = null;
	  
	  
	  YamlReader yamlReader = new YamlReader();
      List<String[]> processList = yamlReader.yamlReading();

      // Print the processList
      /*for (int i = 0; i < processList.size(); i++) {
        String[] process = processList.get(i);
          System.out.println("processList[" + i + "] = (" + process[0] + ", " + process[1] + ", " + process[2] + ", " + process[3] + ")");
      }*/
      
      String[] process = processList.get(0);
      System.out.println("processList[" + 0 + "] = (" + process[0] + ", " + process[1] + ", " + process[2] + ", " + process[3] + ")");
      
      String callbackClassName = (String) process[3];
      
      try {
          // Utilizar reflexión para crear una instancia del callback a partir del nombre de clase
          Class<?> callbackClass = Class.forName(callbackClassName);
          BiganFedListener callback = (BiganFedListener) callbackClass.getDeclaredConstructor().newInstance();

          // Llamar al método handleCallback con el mensaje
          callback.handleCallback(messageMap);

      } 
      catch (Exception e){
              e.printStackTrace();
      }

  }
}
