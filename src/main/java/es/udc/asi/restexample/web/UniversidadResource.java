package es.udc.asi.restexample.web;

import es.udc.asi.restexample.model.exception.NotFoundException;
import es.udc.asi.restexample.model.service.UniversidadService;
import es.udc.asi.restexample.model.service.dto.EstudioDTO;
import es.udc.asi.restexample.model.service.dto.UniversidadDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/universidades")
public class UniversidadResource {
  @Autowired
  private UniversidadService universidadService;

  @GetMapping
  public List<UniversidadDTO> findAllUniversidades() {
    return universidadService.findAllUniversidades();
  }

  @GetMapping("/{id}/estudios")
  public List<EstudioDTO> findAllByUniversidad(@PathVariable Long id) throws NotFoundException {
    return universidadService.findAllByUniversidad(id);
  }

}
