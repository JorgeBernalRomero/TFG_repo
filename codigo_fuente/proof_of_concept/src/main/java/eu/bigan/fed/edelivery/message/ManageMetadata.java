package eu.bigan.fed.edelivery.message;

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
