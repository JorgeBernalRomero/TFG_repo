package eu.bigan.fed.edelivery.utils;

public class StatusManager{
	private static int statusSM = 0;
	
	public static int getStatus() {
		return statusSM;
	}
	
	public static void setStatus(int status){
		StatusManager.statusSM = status;
	}
}