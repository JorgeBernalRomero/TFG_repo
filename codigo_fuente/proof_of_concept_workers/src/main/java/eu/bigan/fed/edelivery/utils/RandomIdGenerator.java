package eu.bigan.fed.edelivery.utils;

import java.security.SecureRandom;

public class RandomIdGenerator {

    private static final SecureRandom random = new SecureRandom();

    
    public int generateRandomId() {
    	  byte[] bytes = new byte[4];
    	  random.nextBytes(bytes);

    	  int id = 0;
    	  for (byte b : bytes) {
    	    id = id << 8 | (b & 0xFF);
    	  }

    	  return id;
    	}

}
