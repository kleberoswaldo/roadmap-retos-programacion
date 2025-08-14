package ec.gob.miduvi.beneficiarios.api.controller;

import ec.gob.miduvi.beneficiarios.api.dto.BeneficiarioRequest;
import ec.gob.miduvi.beneficiarios.api.dto.BeneficiarioResponse;
import ec.gob.miduvi.beneficiarios.api.mapper.BeneficiarioMapper;
import ec.gob.miduvi.beneficiarios.domain.entity.Beneficiario;
import ec.gob.miduvi.beneficiarios.domain.enums.EstadoBeneficiario;
import ec.gob.miduvi.beneficiarios.domain.service.BeneficiarioService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/beneficiarios")
public class BeneficiarioController {

	private final BeneficiarioService beneficiarioService;

	public BeneficiarioController(BeneficiarioService beneficiarioService) {
		this.beneficiarioService = beneficiarioService;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public BeneficiarioResponse crear(@Valid @RequestBody BeneficiarioRequest request) {
		Beneficiario creado = beneficiarioService.crear(BeneficiarioMapper.toEntity(request));
		return BeneficiarioMapper.toResponse(creado);
	}

	@GetMapping("/{id}")
	public BeneficiarioResponse obtener(@PathVariable Long id) {
		return BeneficiarioMapper.toResponse(beneficiarioService.obtenerPorId(id));
	}

	@GetMapping
	public Page<BeneficiarioResponse> listar(@RequestParam(value = "estado", required = false) EstadoBeneficiario estado,
											  Pageable pageable) {
		return beneficiarioService.listar(estado, pageable)
				.map(BeneficiarioMapper::toResponse);
	}

	@PutMapping("/{id}")
	public BeneficiarioResponse actualizar(@PathVariable Long id, @Valid @RequestBody BeneficiarioRequest request) {
		Beneficiario actualizado = beneficiarioService.actualizar(id, BeneficiarioMapper.toEntity(request));
		return BeneficiarioMapper.toResponse(actualizado);
	}

	@PatchMapping("/{id}/estado")
	public BeneficiarioResponse cambiarEstado(@PathVariable Long id, @RequestParam("valor") EstadoBeneficiario nuevoEstado) {
		return BeneficiarioMapper.toResponse(beneficiarioService.cambiarEstado(id, nuevoEstado));
	}
}