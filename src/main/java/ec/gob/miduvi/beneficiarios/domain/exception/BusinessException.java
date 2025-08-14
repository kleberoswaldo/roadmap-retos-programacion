package ec.gob.miduvi.beneficiarios.domain.exception;

public class BusinessException extends RuntimeException {
	public BusinessException(String message) {
		super(message);
	}
}