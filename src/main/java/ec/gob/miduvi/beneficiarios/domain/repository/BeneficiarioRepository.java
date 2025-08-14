package ec.gob.miduvi.beneficiarios.domain.repository;

import ec.gob.miduvi.beneficiarios.domain.entity.Beneficiario;
import ec.gob.miduvi.beneficiarios.domain.enums.EstadoBeneficiario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BeneficiarioRepository extends JpaRepository<Beneficiario, Long> {
	Optional<Beneficiario> findByIdentificacion(String identificacion);
	Page<Beneficiario> findByEstadoBeneficiario(EstadoBeneficiario estadoBeneficiario, Pageable pageable);
}