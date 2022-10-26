package com.otsi.retail.authservice.Exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.otsi.retail.authservice.errors.ErrorResponse;

import io.netty.channel.unix.Errors.NativeIoException;
import reactor.netty.http.client.PrematureCloseException;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	private Logger log = LogManager.getLogger(CustomExceptionHandler.class);

	@ExceptionHandler(value = RecordNotFoundException.class)
	public ResponseEntity<Object> handleRecordNotFoundException(RecordNotFoundException recordNotException) {
		ErrorResponse<?> error = new ErrorResponse<>(recordNotException.getStatusCode(),
				recordNotException.getMessage());
		log.error("error response is:" + error + " status:" + recordNotException.getStatusCode());
		return new ResponseEntity<Object>(error, HttpStatus.valueOf(recordNotException.getStatusCode()));
	}

	@ExceptionHandler(value = DuplicateRecordException.class)
	public ResponseEntity<Object> handleDuplicateRecordException(DuplicateRecordException duplicateRecordException) {
		ErrorResponse<?> error = new ErrorResponse<>(duplicateRecordException.DRF_STATUSCODE,
				duplicateRecordException.DRF_DESCRIPTION);
		log.error("error response is:" + error + " status:" + duplicateRecordException.getStatusCode());
		return new ResponseEntity<Object>(error, HttpStatus.valueOf(duplicateRecordException.getStatusCode()));
	}

	@ExceptionHandler(value = PrematureCloseException.class)
	public ResponseEntity<Object> handlePrematureCloseException(PrematureCloseException prematureCloseException) {
		ErrorResponse<?> error = new ErrorResponse<>(HttpStatus.EXPECTATION_FAILED.value(),
				prematureCloseException.getMessage());
		log.error("error response is:" + error);
		return new ResponseEntity<Object>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(value = NativeIoException.class)
	public ResponseEntity<Object> handleNativeIoException(NativeIoException nativeIoException) {
		ErrorResponse<?> error = new ErrorResponse<>(HttpStatus.EXPECTATION_FAILED.value(),
				nativeIoException.getMessage());
		log.error("error response is:" + error);
		return new ResponseEntity<Object>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(ResponseStatusException.class)
	public ResponseEntity<?> handleResponseStatusException(ResponseStatusException ex) {
		ErrorResponse<?> error = new ErrorResponse<>(ex.getRawStatusCode(), ex.getReason());
		log.error("error response is:" + error + " status:" + ex.getStatus());
		return new ResponseEntity<>(error, ex.getStatus());
	}

	@ExceptionHandler(value = PlanExpirationException.class)
	public ResponseEntity<Object> handlePlanExpirationException(PlanExpirationException planExpirationException) {
		ErrorResponse<?> error = new ErrorResponse<>(planExpirationException.getStatusCode(),
				planExpirationException.getMessage());
		log.error("error response is:" + error + " status:" + planExpirationException.getStatusCode());
		return new ResponseEntity<Object>(error, HttpStatus.valueOf(planExpirationException.getStatusCode()));
	}

}
