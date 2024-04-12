package eu.bigan.fed.edelivery.message;

import java.security.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class MessageRegistry {

	private List<ManageMetadata> messagesList = new ArrayList<>();

    public void addMessage(int idMessage, String origin, String destination, BiganFedListener callback, Timestamp timestamp, int status) {
        ManageMetadata metadata = new ManageMetadata(idMessage, origin, destination, callback, timestamp, status);
        messagesList.add(metadata);
    }

    public ManageMetadata getMessageFromListById(int idMessage) {
        return messagesList.stream()
                .filter(m -> m.getIdMessage() == idMessage)
                .findFirst()
                .orElse(null);
    }

    //esta nos hace falta???
    public List<ManageMetadata> getAllMessages() {
        return new ArrayList<>(messagesList); // Return a copy to avoid modification
    }
}
