package ec.gob.miduvi.beneficiarios.api.mapper;

import ec.gob.miduvi.beneficiarios.api.dto.BeneficiarioRequest;
import ec.gob.miduvi.beneficiarios.api.dto.BeneficiarioResponse;
import ec.gob.miduvi.beneficiarios.domain.entity.Beneficiario;

public class BeneficiarioMapper {

	public static Beneficiario toEntity(BeneficiarioRequest request) {
		Beneficiario b = new Beneficiario();
		b.setIdentificacion(request.getIdentificacion());
		b.setNombres(request.getNombres());
		b.setApellidos(request.getApellidos());
		b.setFechaNacimiento(request.getFechaNacimiento());
		b.setEstadoCivil(request.getEstadoCivil());
		b.setIngresosMensuales(request.getIngresosMensuales());
		b.setProvincia(request.getProvincia());
		b.setCanton(request.getCanton());
		b.setParroquia(request.getParroquia());
		b.setDireccion(request.getDireccion());
		b.setTelefono(request.getTelefono());
		b.setCorreo(request.getCorreo());
		return b;
	}

	public static BeneficiarioResponse toResponse(Beneficiario entity) {
		BeneficiarioResponse r = new BeneficiarioResponse();
		r.setId(entity.getId());
		r.setIdentificacion(entity.getIdentificacion());
		r.setNombres(entity.getNombres());
		r.setApellidos(entity.getApellidos());
		r.setFechaNacimiento(entity.getFechaNacimiento());
		r.setEstadoCivil(entity.getEstadoCivil());
		r.setIngresosMensuales(entity.getIngresosMensuales());
		r.setProvincia(entity.getProvincia());
		r.setCanton(entity.getCanton());
		r.setParroquia(entity.getParroquia());
		r.setDireccion(entity.getDireccion());
		r.setTelefono(entity.getTelefono());
		r.setCorreo(entity.getCorreo());
		r.setEstadoBeneficiario(entity.getEstadoBeneficiario());
		r.setCreadoEn(entity.getCreadoEn());
		r.setActualizadoEn(entity.getActualizadoEn());
		return r;
	}
}