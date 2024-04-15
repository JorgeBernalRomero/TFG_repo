package eu.bigan.fed.edelivery.message;

import java.security.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ManageMetadata {

    private String messageId;
    private String destNode;
    private BiganFedListener callback;
    private String timestamp;
    private int status;

    
    public ManageMetadata(String messageId, String destNode, BiganFedListener callback, String timestamp, int status) {
        this.messageId = messageId;
        this.destNode = destNode;
        this.callback = callback;
        this.timestamp = timestamp;
        this.status = status;
    }


	public String getIdMessage() {
		return messageId;
	}
}
