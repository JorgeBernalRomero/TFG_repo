package eu.bigan.fed.edelivery.message;

public class ReturnMetadata {
    private static ReturnMetadata ReturnMetadata = null;
    private static String messageId;
    private static String destNode;

    
    public static ReturnMetadata getInstance(){
        return ReturnMetadata;
    }

	public static String getMessageId() {
		return messageId;
	}

    public static void setMessageId(String messageIdInput) {
		messageId=messageIdInput;
	}
	
	public static String getDestNode() {
		return destNode;
	}

    public static void setDestNode(String destNodeInput) {
		destNode=destNodeInput;
	}
}
