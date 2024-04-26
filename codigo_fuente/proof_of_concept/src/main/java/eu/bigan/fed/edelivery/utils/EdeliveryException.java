package eu.bigan.fed.edelivery.utils;

/**
 * Texto sobre lo que hace esta clase.
 * @author jorgebernalromero
 *
 */
public class EdeliveryException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5956708608033811709L;

	/**
	 * 
	 */
	public EdeliveryException () {
		
	}

    /**
     * 
     * @param message
     * @param ex
     */
	public EdeliveryException (String message, Exception ex) {
		super(message, ex);
		
	}
	
	/**
	 * 
	 * @param message
	 */
	public EdeliveryException (String message) {
		super(message);
		
	}
}
