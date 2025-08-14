package ec.gob.miduvi.beneficiarios.api.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class BeneficiarioRequest {
	@NotBlank
	@Size(max = 20)
	private String identificacion;

	@NotBlank
	@Size(max = 120)
	private String nombres;

	@NotBlank
	@Size(max = 120)
	private String apellidos;

	private LocalDate fechaNacimiento;

	@Size(max = 20)
	private String estadoCivil;

	@Digits(integer = 13, fraction = 2)
	private BigDecimal ingresosMensuales;

	@Size(max = 60)
	private String provincia;

	@Size(max = 60)
	private String canton;

	@Size(max = 60)
	private String parroquia;

	@Size(max = 200)
	private String direccion;

	@Size(max = 30)
	private String telefono;

	@Email
	@Size(max = 120)
	private String correo;
}