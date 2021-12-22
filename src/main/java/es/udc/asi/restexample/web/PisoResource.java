package es.udc.asi.restexample.web;

import es.udc.asi.restexample.model.domain.Piso;
import es.udc.asi.restexample.model.exception.NotFoundException;
import es.udc.asi.restexample.model.exception.OperationNotAllowed;
import es.udc.asi.restexample.model.service.PisoService;
import es.udc.asi.restexample.model.service.dto.PisoDTO;
import es.udc.asi.restexample.web.exceptions.IdAndBodyNotMatchingOnUpdateException;
import es.udc.asi.restexample.web.exceptions.RequestBodyNotValidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/pisos")
public class PisoResource {
  @Autowired
  PisoService pisoService;

  @GetMapping
  public List<PisoDTO> findAll() {
    return pisoService.findAll();
  }

  @GetMapping("/{id}")
  public PisoDTO findById(@PathVariable Long id) throws NotFoundException {
    return pisoService.findById(id);
  }

  @PostMapping
  public PisoDTO create(@RequestBody @Valid PisoDTO piso, Errors errors) throws RequestBodyNotValidException {
    if (errors.hasErrors()) {
      throw new RequestBodyNotValidException(errors);
    }
    return pisoService.create(piso);
  }

  @PutMapping("/{id}")
  public PisoDTO update(@PathVariable Long id, @RequestBody @Valid PisoDTO piso, Errors errors) throws RequestBodyNotValidException,
    IdAndBodyNotMatchingOnUpdateException, OperationNotAllowed, NotFoundException {
    if (errors.hasErrors()) {
      throw new RequestBodyNotValidException(errors);
    }
    if (id != piso.getIdPiso()) {
      throw new IdAndBodyNotMatchingOnUpdateException(Piso.class);
    }
    try {
      pisoService.findById(piso.getIdPiso());
    } catch (NotFoundException e) {
      throw new NotFoundException(id.toString(), Piso.class);
    }
    return pisoService.update(piso);
  }

  @DeleteMapping("{id}")
  public void delete(@PathVariable Long id) throws NotFoundException, OperationNotAllowed {
    pisoService.deleteById(id);
  }

}
