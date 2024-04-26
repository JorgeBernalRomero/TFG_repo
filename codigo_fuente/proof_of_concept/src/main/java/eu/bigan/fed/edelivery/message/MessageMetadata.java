package eu.bigan.fed.edelivery.message;

import eu.bigan.fed.edelivery.utils.GetTimestamp;

public class MessageMetadata {

    private String messageId;
    private String destNode;
    private BiganFedListener callback;
    private String timestamp;
    private int status;

    
    public MessageMetadata(String messageId, String destNode, BiganFedListener callback, int status) {
        this.messageId = messageId;
        this.destNode = destNode;
        this.callback = callback;
        GetTimestamp getTimeStamp = new GetTimestamp();
	    this.timestamp = getTimeStamp.getTimeStamp();
        this.status = status;
    }


	public String getMessageId() {
		return messageId;
	}
	
	public String getDestNode() {
		return destNode;
	}
	
	public BiganFedListener getCallback() {
		return callback;
	}
	
	public String getTimestamp() {
		return timestamp;
	}
	
	public int getStatus() {
		return status;
	}
	
	public void setStatus(int status){
		this.status = status;
	}
}
