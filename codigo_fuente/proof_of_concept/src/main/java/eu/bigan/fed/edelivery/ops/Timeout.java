package eu.bigan.fed.edelivery.ops;

import java.util.concurrent.TimeUnit;

import eu.bigan.fed.edelivery.message.ManageMetadata;


public class Timeout{
	
	public void completeTimeout(int timeout, ManageMetadata actualMessage) {
		try {
			TimeUnit.SECONDS.sleep(timeout);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		actualMessage.setStatus(1);
	}
}