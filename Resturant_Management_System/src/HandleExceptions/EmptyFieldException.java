package HandleExceptions;

public class EmptyFieldException extends Exception {
	
	public EmptyFieldException(String errorMessage, Throwable err) {
		super(errorMessage, err);
	}

}
