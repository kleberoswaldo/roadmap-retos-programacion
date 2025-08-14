package ec.gob.miduvi.beneficiarios.domain.entity;

import ec.gob.miduvi.beneficiarios.domain.enums.EstadoBeneficiario;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "beneficiario",
	uniqueConstraints = {
		@UniqueConstraint(name = "uk_beneficiario_identificacion", columnNames = "identificacion"),
		@UniqueConstraint(name = "uk_beneficiario_correo", columnNames = "correo")
	}
)
public class Beneficiario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@NotBlank
	@Size(max = 20)
	@Column(name = "identificacion", nullable = false, length = 20)
	private String identificacion;

	@NotBlank
	@Size(max = 120)
	@Column(name = "nombres", nullable = false, length = 120)
	private String nombres;

	@NotBlank
	@Size(max = 120)
	@Column(name = "apellidos", nullable = false, length = 120)
	private String apellidos;

	@Column(name = "fecha_nacimiento")
	private LocalDate fechaNacimiento;

	@Size(max = 20)
	@Column(name = "estado_civil", length = 20)
	private String estadoCivil;

	@Digits(integer = 13, fraction = 2)
	@Column(name = "ingresos_mensuales", precision = 15, scale = 2)
	private BigDecimal ingresosMensuales;

	@Size(max = 60)
	@Column(name = "provincia", length = 60)
	private String provincia;

	@Size(max = 60)
	@Column(name = "canton", length = 60)
	private String canton;

	@Size(max = 60)
	@Column(name = "parroquia", length = 60)
	private String parroquia;

	@Size(max = 200)
	@Column(name = "direccion", length = 200)
	private String direccion;

	@Size(max = 30)
	@Column(name = "telefono", length = 30)
	private String telefono;

	@Email
	@Size(max = 120)
	@Column(name = "correo", length = 120)
	private String correo;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "estado_beneficiario", nullable = false, length = 30)
	private EstadoBeneficiario estadoBeneficiario = EstadoBeneficiario.POSTULADO;

	@Column(name = "creado_en", nullable = false)
	private LocalDateTime creadoEn;

	@Column(name = "actualizado_en")
	private LocalDateTime actualizadoEn;

	@Version
	@Column(name = "version")
	private Integer version;

	@PrePersist
	void prePersist() {
		this.creadoEn = LocalDateTime.now();
	}

	@PreUpdate
	void preUpdate() {
		this.actualizadoEn = LocalDateTime.now();
	}
}