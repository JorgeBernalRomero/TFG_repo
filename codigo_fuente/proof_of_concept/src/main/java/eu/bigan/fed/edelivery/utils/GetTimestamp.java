package eu.bigan.fed.edelivery.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GetTimestamp {

	  public String gettingTimeStamp() {
		  Date date = new Date(); // Get the current date and time
		    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"); // Define desired format (yyyy-MM-dd HH:mm:ss.SSS)
		    String formattedTimestamp = formatter.format(date);  // Format the date object
		    long timestampInMilliseconds = date.getTime(); // Get milliseconds since epoch

		    // Combine formatted timestamp and milliseconds into a single string
		    String combinedString = formattedTimestamp + ":" + timestampInMilliseconds;
		    return combinedString;
	  }
}