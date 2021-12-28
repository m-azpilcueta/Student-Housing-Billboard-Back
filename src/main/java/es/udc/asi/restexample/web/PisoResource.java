package es.udc.asi.restexample.web;

import es.udc.asi.restexample.model.domain.Imagen;
import es.udc.asi.restexample.model.domain.Mensaje;
import es.udc.asi.restexample.model.domain.Piso;
import es.udc.asi.restexample.model.exception.ModelException;
import es.udc.asi.restexample.model.exception.NotFoundException;
import es.udc.asi.restexample.model.exception.OperationNotAllowed;
import es.udc.asi.restexample.model.service.PisoService;
import es.udc.asi.restexample.model.service.dto.*;
import es.udc.asi.restexample.web.exceptions.IdAndBodyNotMatchingOnUpdateException;
import es.udc.asi.restexample.web.exceptions.RequestBodyNotValidException;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Set;

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

  @PostMapping("/{id}/preguntas")
  public PisoDTO preguntar(@PathVariable Long id, @RequestBody @Valid TextoMensajesDTO pregunta, Errors errors) throws RequestBodyNotValidException, NotFoundException {
    if (errors.hasErrors()) {
      throw new RequestBodyNotValidException(errors);
    }
    try {
      pisoService.findById(id);
    } catch (NotFoundException e) {
      throw new NotFoundException(id.toString(), Piso.class);
    }
    return pisoService.preguntar(id, pregunta);
  }

  @PostMapping("/{id}/preguntas/{pregunta}")
  public PisoDTO responder(@PathVariable Long id, @PathVariable Long pregunta, @RequestBody @Valid TextoMensajesDTO respuesta, Errors errors) throws RequestBodyNotValidException, NotFoundException, IdAndBodyNotMatchingOnUpdateException, OperationNotAllowed {
    if (errors.hasErrors()) {
      throw new RequestBodyNotValidException(errors);
    }
    try {
      pisoService.findById(id);
    } catch (NotFoundException e) {
      throw new NotFoundException(id.toString(), Piso.class);
    }
    return pisoService.responder(id, pregunta, respuesta);
  }

  @PostMapping("/{id}/imagenes")
  @ResponseStatus(HttpStatus.OK)
  public void guardarImagenes(@PathVariable Long id, @RequestParam Set<MultipartFile> imagenes) throws NotFoundException, OperationNotAllowed {
    try {
      pisoService.findById(id);
    } catch (NotFoundException e) {
      throw new NotFoundException(id.toString(), Piso.class);
    }
    pisoService.guardarImagenes(id, imagenes);
  }

  @GetMapping("/{id}/imagenes/{idImagen}")
  @ResponseStatus(HttpStatus.OK)
  public void cargarImagen(@PathVariable Long id, @PathVariable Long idImagen, HttpServletResponse response) throws ModelException {
    PisoDTO p;
    String path = "";
    try {
      p = pisoService.findById(id);
    } catch (NotFoundException e) {
      throw new NotFoundException(id.toString(), Piso.class);
    }
    for (ImagenDTO i:p.getImagenes()) {
      if (idImagen == i.getIdImagen()) {
         path = i.getPath();
         break;
      }
    }
    ImagenDTO imagenDTO = pisoService.cargarImagen(path);
    try {
      response.setContentType(imagenDTO.getMediaType());
      response.setHeader("Content-disposition", "filename=" + imagenDTO.getPath());
      IOUtils.copy(imagenDTO.getInputStream(), response.getOutputStream());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @PutMapping("/{id}/imagenes/{idImagen}")
  public ImagenDTO actualizarPortada(@PathVariable Long id, @PathVariable Long idImagen, @RequestBody @Valid ActualizarImagenDTO imagen, Errors errors) throws IdAndBodyNotMatchingOnUpdateException, RequestBodyNotValidException {
    if (errors.hasErrors()) {
      throw new RequestBodyNotValidException(errors);
    }
    if (id != imagen.getIdPiso()) {
      throw new IdAndBodyNotMatchingOnUpdateException(Piso.class);
    }
    if (idImagen != imagen.getIdImagen()) {
      throw new IdAndBodyNotMatchingOnUpdateException(Imagen.class);
    }
    return pisoService.actualizarImagen(imagen.getIdImagen(), imagen.isPortada());
  }

  @DeleteMapping("/{id}/imagenes/{idImagen}")
  public void borrarImagen(@PathVariable Long id, @PathVariable Long idImagen) throws ModelException {
    try {
      pisoService.findById(id);
    } catch (NotFoundException e) {
      throw new NotFoundException(id.toString(), Piso.class);
    }
    pisoService.borrarImagen(id, idImagen);
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

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) throws NotFoundException, OperationNotAllowed {
    pisoService.deleteById(id);
  }

}
