package by.vsu.service.exception;

public class ServiceException extends Exception {
	public ServiceException(Throwable cause) {
		super(cause);
	}

	public ServiceException(String message) {
		super(message);
	}
}
