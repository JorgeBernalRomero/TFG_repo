package eu.bigan.fed.edelivery.message;

import java.security.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class MessageRegistry {

	private List<ManageMetadata> messagesList = new ArrayList<>();

    public void addMessage(String idMessage, String destNode, BiganFedListener callback, String timestamp, int status) {
        ManageMetadata metadata = new ManageMetadata(idMessage, destNode, callback, timestamp, status);
        messagesList.add(metadata);
    }

    public ManageMetadata getMessageFromListById(String idMessage) {
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
