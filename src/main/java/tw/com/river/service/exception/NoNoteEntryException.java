package tw.com.river.service.exception;

public class NoNoteEntryException extends ServiceException {

	private static final long serialVersionUID = 2552924024751631825L;

	public NoNoteEntryException() {
		super();
	}

	public NoNoteEntryException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NoNoteEntryException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoNoteEntryException(String message) {
		super(message);
	}

	public NoNoteEntryException(Throwable cause) {
		super(cause);
	}

	
}
