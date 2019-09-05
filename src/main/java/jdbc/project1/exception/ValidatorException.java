package jdbc.project1.exception;

public class ValidatorException extends Exception {
	
	/**
	 * This userdefined exception for validating donor inputs
	 */
	private static final long serialVersionUID = 1L;

	public ValidatorException(String message) {
		super(message);
	}
	
}
