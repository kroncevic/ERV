package hr.tvz.rome.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class SomethingWentWrongException extends RuntimeException {

	public SomethingWentWrongException() {
		super();
	}

	public SomethingWentWrongException(Throwable cause) {
		super(cause);
	}

}
