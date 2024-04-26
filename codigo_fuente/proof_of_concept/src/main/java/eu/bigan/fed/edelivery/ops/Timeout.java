package eu.bigan.fed.edelivery.ops;

import java.util.concurrent.TimeUnit;
import eu.bigan.fed.edelivery.message.MessageMetadata;

/**
 * Texto sobre lo que hace esta clase.
 * @author jorgebernalromero
 *
 */
public class Timeout{
	
	/**
	 * 
	 * @param timeout
	 * @param actualMessage
	 */
	public void completeTimeout(int timeout, MessageMetadata actualMessage) {
		try {
			TimeUnit.SECONDS.sleep(timeout);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		actualMessage.setStatus(1);
	}
}