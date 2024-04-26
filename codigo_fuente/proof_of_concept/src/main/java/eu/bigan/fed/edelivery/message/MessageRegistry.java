package eu.bigan.fed.edelivery.message;

import java.util.ArrayList;
import java.util.List;

public class MessageRegistry {
	
	private static MessageRegistry messageRegistry = new MessageRegistry();
	private List<MessageMetadata> messagesList;
	
	private MessageRegistry() {
		messagesList = new ArrayList<>();
	}
	
	
	public static MessageRegistry getInstance() {
        return messageRegistry;
    }

	
    public void addMessage(MessageMetadata metadata) {
        messagesList.add(metadata);
    }
    
    
    public MessageMetadata getMessageFromListById(String messageId) {
        for (MessageMetadata metadata : messagesList) {
            if (metadata.getMessageId().equals(messageId)) {
                return metadata;
            }
        }
        return null;
    }
    
    
    public List<String[]> listConversionToString(List<MessageMetadata> list) {
    	List<String[]> convertedList = new ArrayList<>();

    	for (MessageMetadata metadata : list) {
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


    public List<MessageMetadata> getAllMessages() {
        return new ArrayList<>(messagesList);
    }
    
}
