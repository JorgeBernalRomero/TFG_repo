package eu.bigan.fed.edelivery.message;

import javax.jms.MapMessage;

/**
 * Texto sobre lo que hace esta interfaz.
 * @author jorgebernalromero
 *
 */
public interface BiganFedListener {

	/**
	 * 
	 * @param messageMap
	 */
	public void handleCallback(MapMessage messageMap);
}
