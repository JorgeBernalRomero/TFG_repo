package eu.bigan.fed.edelivery.message;

import javax.jms.MapMessage;

public interface BiganFedListener {
	
	public void onMessage(MapMessage messageMap);
}
