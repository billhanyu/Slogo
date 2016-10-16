package exception;

public class WrongNumberOfArguments extends Exception{

	private static final long serialVersionUID = 3364574452765769240L;
	
	public WrongNumberOfArguments() {
		
	}

	public WrongNumberOfArguments(String inClass) {
		super(inClass);
	}
}
