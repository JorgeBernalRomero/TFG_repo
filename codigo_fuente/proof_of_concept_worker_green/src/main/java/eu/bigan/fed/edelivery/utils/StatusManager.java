package eu.bigan.fed.edelivery.utils;


public class StatusManager {

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
    
}
