package eu.bigan.fed.edelivery.utils;

import java.math.BigInteger;
import java.security.SecureRandom;

public class RandomIdGenerator {

    private static final SecureRandom random = new SecureRandom();

    
    public static int generateRandomId() {
        byte[] bytes = new byte[4];
        random.nextBytes(bytes);
        
        return new BigInteger(bytes).intValue();
    }
}
