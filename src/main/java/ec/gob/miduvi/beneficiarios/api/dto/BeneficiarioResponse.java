package ec.gob.miduvi.beneficiarios.api.dto;

import ec.gob.miduvi.beneficiarios.domain.enums.EstadoBeneficiario;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class BeneficiarioResponse {
	private Long id;
	private String identificacion;
	private String nombres;
	private String apellidos;
	private LocalDate fechaNacimiento;
	private String estadoCivil;
	private BigDecimal ingresosMensuales;
	private String provincia;
	private String canton;
	private String parroquia;
	private String direccion;
	private String telefono;
	private String correo;
	private EstadoBeneficiario estadoBeneficiario;
	private LocalDateTime creadoEn;
	private LocalDateTime actualizadoEn;
}