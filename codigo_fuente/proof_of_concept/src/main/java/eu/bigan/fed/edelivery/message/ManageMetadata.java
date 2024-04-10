package eu.bigan.fed.edelivery.message;

import java.security.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ManageMetadata {

    private String idMessage;
    private String target;
    private MessageCallback callback;
    private Timestamp timestamp;
    private int status;
    private List<ManageMetadata> messages; // List of associated messages

    public ManageMetadata(String idMessage, String target, MessageCallback callback, Timestamp timestamp, int status) {
        this.idMessage = idMessage;
        this.target = target;
        this.callback = callback;
        this.timestamp = timestamp;
        this.status = status;
        this.messages = new ArrayList<>();
    }

    public String getIdMessage() {
        return idMessage;
    }

    public String getTarget() {
        return target;
    }

    // Getters and setters for other fields

    public void addMessage(ManageMetadata message) {
        messages.add(message);
        if (callback != null) {
            callback.onMessageAdded(message); // Call the callback if implemented
        }
    }

    public List<ManageMetadata> getMessages() {
        return messages;
    }
}
