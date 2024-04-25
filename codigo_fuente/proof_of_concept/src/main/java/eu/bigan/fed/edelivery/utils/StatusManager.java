package eu.bigan.fed.edelivery.utils;


public class StatusManager {
	
	//******************************************REVISAR

    private static int statusId;
    
    public static int getInstance() {
        return statusId;
    }
    
    public static void setStatus(int status) {
        statusId = status;
    }
    
    public static int getStatus(){
        return statusId;
    }
    
    //yo diría que para esto habría que conseguir añadirlo, por el idMessage a la lista de mensajes en el campo status
    
}