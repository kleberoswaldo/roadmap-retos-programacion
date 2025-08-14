package ec.gob.miduvi.beneficiarios.api.handler;

import ec.gob.miduvi.beneficiarios.domain.exception.BusinessException;
import ec.gob.miduvi.beneficiarios.domain.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<?> handleNotFound(NotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(error("NOT_FOUND", ex.getMessage()));
	}

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<?> handleBusiness(BusinessException ex) {
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
				.body(error("BUSINESS_ERROR", ex.getMessage()));
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleValidation(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach(err -> {
			String field = err instanceof FieldError ? ((FieldError) err).getField() : err.getObjectName();
			errors.put(field, err.getDefaultMessage());
		});
		Map<String, Object> body = error("VALIDATION_ERROR", "Datos inválidos");
		body.put("details", errors);
		return ResponseEntity.badRequest().body(body);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleGeneric(Exception ex) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(error("INTERNAL_ERROR", ex.getMessage()));
	}

	private Map<String, Object> error(String code, String message) {
		Map<String, Object> body = new HashMap<>();
		body.put("timestamp", Instant.now().toString());
		body.put("code", code);
		body.put("message", message);
		return body;
	}
}