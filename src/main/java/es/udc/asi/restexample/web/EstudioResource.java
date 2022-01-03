package es.udc.asi.restexample.web;

import es.udc.asi.restexample.model.exception.NotFoundException;
import es.udc.asi.restexample.model.service.EstudioService;
import es.udc.asi.restexample.model.service.dto.EstudioDTO;
import es.udc.asi.restexample.web.exceptions.RequestBodyNotValidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/estudios")
public class EstudioResource {
  @Autowired
  private EstudioService estudioService;

  @PostMapping
  public EstudioDTO crear(@RequestBody @Valid EstudioDTO estudio, Errors errors) throws RequestBodyNotValidException, NotFoundException {
    if (errors.hasErrors()) {
      throw new RequestBodyNotValidException(errors);
    }
    return estudioService.crearEstudio(estudio);
  }
}
