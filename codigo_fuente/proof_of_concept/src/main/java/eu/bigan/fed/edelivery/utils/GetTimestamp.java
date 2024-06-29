package eu.bigan.fed.edelivery.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Texto sobre lo que hace esta clase.
 * @author jorgebernalromero
 *
 */
public class GetTimestamp {
	
	/**
	 * 
	 * @return
	 */
	public String getTimeStamp() {
		Date date = new Date();
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	    String formattedTimestamp = formatter.format(date);
	    long timestampInMilliseconds = date.getTime();

	    String combinedString = formattedTimestamp + ":" + timestampInMilliseconds;
	    
	    return combinedString;
	}
}