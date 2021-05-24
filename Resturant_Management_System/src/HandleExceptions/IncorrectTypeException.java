package HandleExceptions;

public class IncorrectTypeException extends Exception {
	public IncorrectTypeException(String errorMessage, Throwable err) {
		super(errorMessage, err);
	}

}
