package eu.bigan.fed.edelivery.message;

public interface BiganFedListener {
	public void handleCallback(String taskContent, String messageId);
}
