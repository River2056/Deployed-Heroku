package tw.com.river.service.exception;

public class PasswordNotFoundException extends DataNotFoundException {

	private static final long serialVersionUID = -3005170487703387109L;

	public PasswordNotFoundException() {
		super();
	}

	public PasswordNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public PasswordNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public PasswordNotFoundException(String message) {
		super(message);
	}

	public PasswordNotFoundException(Throwable cause) {
		super(cause);
	}

	
}
