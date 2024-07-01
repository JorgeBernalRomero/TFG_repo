package eu.bigan.fed.edelivery.ops;

import java.util.concurrent.TimeUnit;
import eu.bigan.fed.edelivery.message.MessageMetadata;


public class Synchronizer{
	
	public void Synchronize(MessageMetadata prev2Message, MessageMetadata prev1Message) {
		
		int prev2MessageStatus = prev2Message.getStatus();
		int prev1MessageStatus = prev1Message.getStatus();
		
		while (prev2MessageStatus != 2 || prev1MessageStatus != 2) {
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			prev2MessageStatus = prev2Message.getStatus();
			prev1MessageStatus = prev1Message.getStatus();	
		}
	}
}