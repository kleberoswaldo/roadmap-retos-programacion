package ec.gob.miduvi.beneficiarios.domain.exception;

public class NotFoundException extends RuntimeException {
	public NotFoundException(String message) {
		super(message);
	}
}