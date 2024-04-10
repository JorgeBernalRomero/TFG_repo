package eu.bigan.fed.edelivery.message;


public interface MessageCallback {

    void onMessageAdded(ManageMetadata message);

    void onMessageRemoved(ManageMetadata message);
    
    

}