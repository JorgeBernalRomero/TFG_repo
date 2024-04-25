package eu.bigan.fed.edelivery.ops;

import java.util.concurrent.TimeUnit;

import eu.bigan.fed.edelivery.utils.StatusManager;

public class Timeout{
	
	public void completeTimeout(int timeout) {
		try {
			TimeUnit.SECONDS.sleep(timeout);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		StatusManager.setStatus(1);
	}
}