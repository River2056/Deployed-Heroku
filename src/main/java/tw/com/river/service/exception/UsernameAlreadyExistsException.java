package tw.com.river.service.exception;

public class UsernameAlreadyExistsException extends ServiceException {

	private static final long serialVersionUID = -924082014237815124L;

	public UsernameAlreadyExistsException() {
		super();
	}

	public UsernameAlreadyExistsException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UsernameAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
	}

	public UsernameAlreadyExistsException(String message) {
		super(message);
	}

	public UsernameAlreadyExistsException(Throwable cause) {
		super(cause);
	}

	
}
