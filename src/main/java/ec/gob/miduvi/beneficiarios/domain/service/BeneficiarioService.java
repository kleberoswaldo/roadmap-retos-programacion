package ec.gob.miduvi.beneficiarios.domain.service;

import ec.gob.miduvi.beneficiarios.domain.entity.Beneficiario;
import ec.gob.miduvi.beneficiarios.domain.enums.EstadoBeneficiario;
import ec.gob.miduvi.beneficiarios.domain.exception.BusinessException;
import ec.gob.miduvi.beneficiarios.domain.exception.NotFoundException;
import ec.gob.miduvi.beneficiarios.domain.repository.BeneficiarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BeneficiarioService {

	private final BeneficiarioRepository beneficiarioRepository;

	public BeneficiarioService(BeneficiarioRepository beneficiarioRepository) {
		this.beneficiarioRepository = beneficiarioRepository;
	}

	public Beneficiario crear(Beneficiario nuevo) {
		beneficiarioRepository.findByIdentificacion(nuevo.getIdentificacion())
			.ifPresent(b -> { throw new BusinessException("Ya existe un beneficiario con esa identificación"); });
		return beneficiarioRepository.save(nuevo);
	}

	@Transactional(readOnly = true)
	public Beneficiario obtenerPorId(Long id) {
		return beneficiarioRepository.findById(id)
			.orElseThrow(() -> new NotFoundException("Beneficiario no encontrado"));
	}

	@Transactional(readOnly = true)
	public Page<Beneficiario> listar(EstadoBeneficiario estado, Pageable pageable) {
		if (estado != null) {
			return beneficiarioRepository.findByEstadoBeneficiario(estado, pageable);
		}
		return beneficiarioRepository.findAll(pageable);
	}

	public Beneficiario actualizar(Long id, Beneficiario cambios) {
		Beneficiario actual = obtenerPorId(id);
		actual.setNombres(cambios.getNombres());
		actual.setApellidos(cambios.getApellidos());
		actual.setFechaNacimiento(cambios.getFechaNacimiento());
		actual.setEstadoCivil(cambios.getEstadoCivil());
		actual.setIngresosMensuales(cambios.getIngresosMensuales());
		actual.setProvincia(cambios.getProvincia());
		actual.setCanton(cambios.getCanton());
		actual.setParroquia(cambios.getParroquia());
		actual.setDireccion(cambios.getDireccion());
		actual.setTelefono(cambios.getTelefono());
		actual.setCorreo(cambios.getCorreo());
		return beneficiarioRepository.save(actual);
	}

	public Beneficiario cambiarEstado(Long id, EstadoBeneficiario nuevoEstado) {
		Beneficiario actual = obtenerPorId(id);
		actual.setEstadoBeneficiario(nuevoEstado);
		return beneficiarioRepository.save(actual);
	}
}