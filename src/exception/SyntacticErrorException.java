package exception;

public class SyntacticErrorException extends Exception{

	private static final long serialVersionUID = 1909365701961600127L;
	
	public SyntacticErrorException() {

	}
	
	public SyntacticErrorException(String msg) {
		super(msg);
	}

}
