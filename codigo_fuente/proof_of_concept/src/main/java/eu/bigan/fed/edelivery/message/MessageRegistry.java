package eu.bigan.fed.edelivery.message;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import eu.bigan.fed.edelivery.utils.YamlReader;

/**
 * Texto sobre lo que hace esta clase.
 * @author jorgebernalromero
 *
 */
public class MessageRegistry {
	
	final Logger logger = LogManager.getLogger(MessageRegistry.class);
	
	private static MessageRegistry messageRegistry = new MessageRegistry();
	private List<MessageMetadata> messagesList;
	
	/**
	 * 
	 */
	private MessageRegistry() {
		messagesList = new ArrayList<>();
	}
	
	/**
	 * 
	 * @return
	 */
	public static MessageRegistry getInstance() {
        return messageRegistry;
    }

	/**
	 * 
	 * @param metadata
	 */
    public void addMessage(MessageMetadata metadata) {
        messagesList.add(metadata);
        logger.info("Mensaje añadido correctamente");
    }
    
    /**
     * 
     * @param messageId
     * @return
     */
    public MessageMetadata getMessageFromListById(String messageId) {
        for (MessageMetadata metadata : messagesList) {
            if (metadata.getMessageId().equals(messageId)) {
                return metadata;
            }
        }
        return null;
    }
    
    /**
     * 
     * @param list
     * @return
     */
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

    /**
     * 
     * @return
     */
    public List<MessageMetadata> getAllMessages() {
        return new ArrayList<>(messagesList);
    }
    
}
