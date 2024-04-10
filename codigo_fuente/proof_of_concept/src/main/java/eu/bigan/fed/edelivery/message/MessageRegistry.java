package eu.bigan.fed.edelivery.message;

import java.security.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class MessageRegistry {

    private static List<ManageMetadata> messagesList = new ArrayList<>();

    public static void addMessage(int idMessage, String origin, String destination, Timestamp timestamp, int status) {
        ManageMetadata metadata = new ManageMetadata(idMessage, origin, destination, timestamp, status);
        messagesList.add(metadata);
    }

    public static ManageMetadata getMessageFromListById(int idMessage) {
    	
        return MessageRegistry.messagesList.stream()
                .filter(m -> m.getIdMessage() == idMessage)
                .findFirst()
                .orElse(null);
    }

    //esta nos hace falta???
    public static List<ManageMetadata> getAllMessages() {
        return new ArrayList<>(messagesList); // Return a copy to avoid modification
    }
}
