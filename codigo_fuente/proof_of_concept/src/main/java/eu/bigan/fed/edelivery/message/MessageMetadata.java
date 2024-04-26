package eu.bigan.fed.edelivery.message;

import eu.bigan.fed.edelivery.utils.GetTimestamp;

/**
 * Texto sobre lo que hace esta clase.
 * @author jorgebernalromero
 *
 */
public class MessageMetadata {

    private String messageId;
    private String destNode;
    private BiganFedListener callback;
    private String timestamp;
    private int status;

    /**
     * 
     * @param messageId
     * @param destNode
     * @param callback
     * @param status
     */
    public MessageMetadata(String messageId, String destNode, BiganFedListener callback, int status) {
        this.messageId = messageId;
        this.destNode = destNode;
        this.callback = callback;
        GetTimestamp getTimeStamp = new GetTimestamp();
	    this.timestamp = getTimeStamp.getTimeStamp();
        this.status = status;
    }

    /**
     * 
     * @return
     */
	public String getMessageId() {
		return messageId;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getDestNode() {
		return destNode;
	}
	
	/**
	 * 
	 * @return
	 */
	public BiganFedListener getCallback() {
		return callback;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getTimestamp() {
		return timestamp;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getStatus() {
		return status;
	}
	
	/**
	 * 
	 * @param status
	 */
	public void setStatus(int status){
		this.status = status;
	}
}
