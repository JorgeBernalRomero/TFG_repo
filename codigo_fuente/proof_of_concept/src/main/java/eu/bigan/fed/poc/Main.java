package eu.bigan.fed.poc;

import javax.jms.MessageProducer;
import javax.jms.Session;

import eu.bigan.fed.edelivery.jms.*;


public class Main {

  public static void main(String[] args) {
    
	  System.out.println("empieza el main");
	  
	  SessionBuilder sessionBuilder = new SessionBuilder();
	  Session session = sessionBuilder.createSession();
	  
	  
	  ConsumerBuilder consumerBuilder = new ConsumerBuilder();
	  consumerBuilder.createConsumer(session);
	  
	  SenderBuilder senderBuilder = new SenderBuilder();
	  MessageProducer producer = senderBuilder.createProducer(session);
	  
	  
	  Sender sender = new Sender();
	  sender.sending(session, "domibus-green", producer);
	  sender.sending(session, "domibus-pink", producer);
	  
    
  }
}
