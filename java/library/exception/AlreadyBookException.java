package library.exception;

public class AlreadyBookException extends RuntimeException{

	public AlreadyBookException(String msg) {
		super(msg);
	}
}
