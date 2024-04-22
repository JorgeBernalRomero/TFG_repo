package eu.bigan.fed.edelivery.message;

import java.util.ArrayList;
import java.util.List;

public class MessageRegistry {
	
	private static MessageRegistry messageRegistry = new MessageRegistry();
	private List<ManageMetadata> messagesList;
	
	private MessageRegistry() {
		messagesList = new ArrayList<>();
	}
	
	
	public static MessageRegistry getInstance() {
        return messageRegistry;
    }

	
    public void addMessage(String messageId, String destNode, BiganFedListener callback, String timestamp, int status) {
        ManageMetadata metadata = new ManageMetadata(messageId, destNode, callback, timestamp, status);
        messagesList.add(metadata);
    }
    
    
    public ManageMetadata getMessageFromListById(String messageId) {
        for (ManageMetadata metadata : messagesList) {
            if (metadata.getMessageId().equals(messageId)) {
                return metadata;
            }
        }
        return null;
    }
    
    
    public List<String[]> listConversionToString(List<ManageMetadata> list) {
    	List<String[]> convertedList = new ArrayList<>();

    	for (ManageMetadata metadata : list) {
    		String[] stringArray = new String[5];
    		stringArray[0] = metadata.getMessageId();
    		stringArray[1] = metadata.getDestNode();
    		BiganFedListener callback = metadata.getCallback();
    		stringArray[2] = callback.toString();
    		stringArray[3] = metadata.getTimestamp();
    		stringArray[4] = String.valueOf(metadata.getStatus());

    		convertedList.add(stringArray);
    	}

    	return convertedList;
    }


    public List<ManageMetadata> getAllMessages() {
        return new ArrayList<>(messagesList);
    }
    
}
