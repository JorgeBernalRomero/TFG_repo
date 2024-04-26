package eu.bigan.fed.edelivery.message;

/**
 * Texto sobre lo que hace esta interfaz.
 * @author jorgebernalromero
 *
 */
public interface BiganFedListener {
	public void handleCallback(String taskContent, String messageId);
}
