package es.udc.asi.restexample.model.service;

import es.udc.asi.restexample.model.domain.*;
import es.udc.asi.restexample.model.exception.ModelException;
import es.udc.asi.restexample.model.exception.NotFoundException;
import es.udc.asi.restexample.model.exception.OperationNotAllowed;
import es.udc.asi.restexample.model.repository.ImagenDao;
import es.udc.asi.restexample.model.repository.MensajeDao;
import es.udc.asi.restexample.model.repository.PisoDao;
import es.udc.asi.restexample.model.repository.UserDao;
import es.udc.asi.restexample.model.service.dto.ImagenDTO;
import es.udc.asi.restexample.model.service.dto.PisoDTO;
import es.udc.asi.restexample.model.service.dto.PreguntaDTO;
import es.udc.asi.restexample.model.service.dto.UserDTOPrivate;
import es.udc.asi.restexample.model.service.util.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class PisoService {
  @Autowired
  private PisoDao pisoDao;

  @Autowired
  private UserDao userDao;

  @Autowired
  private ImagenDao imagenDao;

  @Autowired
  private MensajeDao mensajeDao;

  @Autowired
  private UserService userService;

  @Autowired
  private ImageService imageService;

  public List<PisoDTO> findAll() {
    return pisoDao.findAll().stream().map(piso -> new PisoDTO(piso)).collect(Collectors.toList());
  }

  public PisoDTO findById(Long id) throws NotFoundException {
    Piso p = pisoDao.findById(id);
    if (p == null) throw new NotFoundException(id.toString(), Piso.class);
    return new PisoDTO(p);
  }

  private void setEnums(Piso p, PisoDTO piso) throws IllegalArgumentException {
    try {
      p.setLocalidad(Localidad.valueOf(piso.getLocalidad().toUpperCase()));
    } catch (IllegalArgumentException e) {
      p.setLocalidad(Localidad.DESCONOCIDO);
    }
    try {
      p.setProvincia(Provincia.valueOf(piso.getProvincia().toUpperCase()));
    } catch (IllegalArgumentException e) {
      p.setProvincia(Provincia.DESCONOCIDO);
    }
  }

  @PreAuthorize("hasAuthority('USER')")
  @Transactional(readOnly = false)
  public PisoDTO create(PisoDTO piso) throws IllegalArgumentException {
    Piso p = new Piso(piso.isAmueblado(), piso.getCalle(), piso.getNombre(), piso.getCodigoPostal(), piso.getDescripcion(), piso.isDisponible(),
      piso.getImporte(), piso.getNumero(), piso.getPisoLetra(), piso.getSuperficie(), piso.getHabitaciones(), piso.getPersonas());
    setEnums(p, piso);
    p.setAnunciante(userDao.findById(userService.getCurrentUserWithAuthority().getId()));
    pisoDao.create(p);
    return new PisoDTO(p);
  }

  @PreAuthorize("hasAuthority('USER')")
  @Transactional(readOnly = false)
  public PisoDTO preguntar(Long id, PreguntaDTO pregunta) {
    Piso p = pisoDao.findById(id);
    Mensaje m = new Mensaje(pregunta);
    m.setUsuario(userDao.findById(userService.getCurrentUserWithAuthority().getId()));
    m.setPregunta(null);
    m.setRespuesta(null);
    mensajeDao.create(m);
    p.getMensajes().add(m);
    pisoDao.update(p);
    return new PisoDTO(p);
  }

  @PreAuthorize("isAuthenticated()")
  @Transactional(readOnly = false)
  public void guardarImagenes(Long id, Set<MultipartFile> imagenes) throws OperationNotAllowed {
    Piso p = pisoDao.findById(id);
    UserDTOPrivate currentUser = userService.getCurrentUserWithAuthority();
    if (!currentUser.getId().equals(p.getAnunciante().getIdUsuario())) {
      throw new OperationNotAllowed("Current user does not match piso creator");
    }
    Set<Imagen> imagenesdb = new HashSet<>();
    imagenes.forEach(imagen -> {
      String path;
      try {
        path = imageService.saveImage(imagen);
      } catch (ModelException e) {
        e.printStackTrace();
        return;
      }
      Imagen i = new Imagen(imagen.getOriginalFilename(), path);
      imagenDao.create(i);
      imagenesdb.add(i);
    });
    p.setImagenes(imagenesdb);
    pisoDao.update(p);
  }

  public ImagenDTO cargarImagen(String path) throws ModelException {
    return imageService.getImage(path);
  }

  @PreAuthorize("isAuthenticated()")
  @Transactional(readOnly = false)
  public ImagenDTO actualizarImagen(Long idImagen, boolean portada) {
    Imagen i = imagenDao.find(idImagen);
    i.setPortada(portada);
    imagenDao.update(i);
    return new ImagenDTO(i.getIdImagen(), i.getNombre(), i.getPath(), i.isPortada());
  }

  @PreAuthorize("isAuthenticated()")
  @Transactional(readOnly = false)
  public void borrarImagen(Long idPiso, Long idImagen) throws ModelException {
    Imagen i, borraIm;
    Piso p = pisoDao.findById(idPiso);
    if (p == null) throw new NotFoundException(idPiso.toString(), Piso.class);
    if ((i = imagenDao.find(idImagen)) == null) throw new NotFoundException(idImagen.toString(), Imagen.class);
    UserDTOPrivate currentUser = userService.getCurrentUserWithAuthority();
    if (!currentUser.getId().equals(p.getAnunciante().getIdUsuario())) {
      throw new OperationNotAllowed("Current user does not match piso creator");
    }
    imageService.deleteImage(i.getPath());
    for (Imagen im : p.getImagenes()) {
      if (idImagen == im.getIdImagen()) {
        borraIm = im;
        p.getImagenes().remove(borraIm);
        break;
      }
    }
    imagenDao.delete(i);
  }

  @PreAuthorize("isAuthenticated()")
  @Transactional(readOnly = false)
  public PisoDTO update(PisoDTO piso) throws IllegalArgumentException, OperationNotAllowed {
    Piso p = pisoDao.findById(piso.getIdPiso());
    p.setAmueblado(piso.isAmueblado());
    p.setCalle(piso.getCalle());
    p.setNombre(piso.getNombre());
    p.setCodigoPostal(piso.getCodigoPostal());
    p.setDescripcion(piso.getDescripcion());
    p.setDisponible(piso.isDisponible());
    p.setImporte(piso.getImporte());
    p.setNumero(piso.getNumero());
    p.setPisoLetra(piso.getPisoLetra());
    p.setSuperficie(piso.getSuperficie());
    p.setHabitaciones(piso.getHabitaciones());
    p.setPersonas(piso.getPersonas());
    setEnums(p, piso);
    UserDTOPrivate currentUser = userService.getCurrentUserWithAuthority();
    if (!currentUser.getId().equals(p.getAnunciante().getIdUsuario())) {
      throw new OperationNotAllowed("Current user does not match piso creator");
    }
    pisoDao.update(p);
    return new PisoDTO(p);
  }

  @PreAuthorize("isAuthenticated()")
  @Transactional(readOnly = false)
  public void deleteById(Long id) throws NotFoundException, OperationNotAllowed {
    Piso p = pisoDao.findById(id);
    if (p == null) throw new NotFoundException(id.toString(), Piso.class);
    UserDTOPrivate currentUser = userService.getCurrentUserWithAuthority();
    if (!currentUser.getId().equals(p.getAnunciante().getIdUsuario())) {
      throw new OperationNotAllowed("Current user does not match piso creator");
    }
    pisoDao.delete(p);
  }
}
