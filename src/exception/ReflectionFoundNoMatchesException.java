package exception;

public class ReflectionFoundNoMatchesException extends Exception {
	
	public ReflectionFoundNoMatchesException() {
		
	}

	public ReflectionFoundNoMatchesException(String inClass) {
		super(inClass);
	}

	private static final long serialVersionUID = -6180712680373051087L;

}
