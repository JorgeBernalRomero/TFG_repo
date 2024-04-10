package eu.bigan.fed.edelivery.message;

import java.security.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class MessageRegistry {

    private static List<ManageMetadata> messages = new ArrayList<>();

    public static void addMessage(String idMessage, String target, MessageCallback callback, Timestamp timestamp, int status) {
        ManageMetadata metadata = new ManageMetadata(idMessage, target, callback, timestamp, status);
        messages.add(metadata);
    }

    public static ManageMetadata getMessageFromList(String idMessage) {
        return messages.stream()
                .filter(m -> m.getIdMessage().equals(idMessage))
                .findFirst()
                .orElse(null);
    }

    public static List<ManageMetadata> getAllMessages() {
        return new ArrayList<>(messages); // Return a copy to avoid modification
    }
}
