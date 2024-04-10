package eu.bigan.fed.edelivery.message;

import java.security.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ManageMetadata {

    private int idMessage;
    private String origin;
    private String destination;
    
    //lo haré un poco más adelante
    //private MessageCallback callback;
    
    private Timestamp timestamp;
    private int status;

    
    public ManageMetadata(int idMessage, String origin, String destination, Timestamp timestamp, int status) {
        this.idMessage = idMessage;
        this.origin = origin;
        this.destination = destination;
        //this.callback = callback;
        this.timestamp = timestamp;
        this.status = status;
    }


	public int getIdMessage() {
		return idMessage;
	}
}
