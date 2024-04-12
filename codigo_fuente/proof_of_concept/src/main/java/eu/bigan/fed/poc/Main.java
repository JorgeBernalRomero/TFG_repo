package eu.bigan.fed.poc;

import java.security.Timestamp;

import javax.jms.MessageProducer;
import javax.jms.Session;
import eu.bigan.fed.edelivery.jms.*;
import eu.bigan.fed.edelivery.message.*;
import eu.bigan.fed.edelivery.utils.*;



public class Main {

  public static void main(String[] args) {
    
	  System.out.println("empieza el main");
	  
	  SessionBuilder sessionBuilder = new SessionBuilder();
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
	  
	  
	  sender.sending(session, "domibus-pink", producer);
	  
    
  }
}
