package es.udc.asi.restexample.model.service;

import es.udc.asi.restexample.model.domain.Localidad;
import es.udc.asi.restexample.model.domain.Piso;
import es.udc.asi.restexample.model.domain.Provincia;
import es.udc.asi.restexample.model.exception.NotFoundException;
import es.udc.asi.restexample.model.exception.OperationNotAllowed;
import es.udc.asi.restexample.model.repository.PisoDao;
import es.udc.asi.restexample.model.repository.UserDao;
import es.udc.asi.restexample.model.service.dto.PisoDTO;
import es.udc.asi.restexample.model.service.dto.UserDTOPrivate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class PisoService {
  @Autowired
  private PisoDao pisoDao;

  @Autowired
  private UserDao userDao;

  @Autowired
  private UserService userService;

  public List<PisoDTO> findAll() {
    return pisoDao.findAll().stream().map(piso -> new PisoDTO(piso)).collect(Collectors.toList());
  }

  public PisoDTO findById(Long id) throws NotFoundException {
    Piso p = pisoDao.findById(id);
    if (p == null) throw new NotFoundException(id.toString(), Piso.class);
    return new PisoDTO(p);
  }

  @PreAuthorize("isAuthenticated()")
  @Transactional(readOnly = false)
  public PisoDTO create(PisoDTO piso) throws IllegalArgumentException {
    Piso p = new Piso(piso.isAmueblado(), piso.getCalle(), piso.getNombre(), piso.getCodigoPostal(), piso.getFechaPublicacion(), piso.getDescripcion(), piso.isDisponible(),
      piso.getImporte(), piso.getNumero(), piso.getPisoLetra(), piso.getSuperficie(), piso.getHabitaciones(), piso.getPersonas());
    try {
      p.setLocalidad(Localidad.valueOf(piso.getLocalidad()));
    } catch (IllegalArgumentException e) {
      p.setLocalidad(Localidad.DESCONOCIDO);
    }
    try {
      p.setProvincia(Provincia.valueOf(piso.getProvincia()));
    } catch (IllegalArgumentException e) {
      p.setProvincia(Provincia.DESCONOCIDO);
    }
    p.setAnunciante(userDao.findById(piso.getAnunciante().getId()));
    pisoDao.create(p);
    return new PisoDTO(p);
  }

  @PreAuthorize("isAuthenticated()")
  @Transactional(readOnly = false)
  public PisoDTO update(PisoDTO piso) throws IllegalArgumentException {
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
    try {
      p.setLocalidad(Localidad.valueOf(piso.getLocalidad()));
    } catch (IllegalArgumentException e) {
      p.setLocalidad(Localidad.DESCONOCIDO);
    }
    try {
      p.setProvincia(Provincia.valueOf(piso.getProvincia()));
    } catch (IllegalArgumentException e) {
      p.setProvincia(Provincia.DESCONOCIDO);
    }
    pisoDao.update(p);
    return new PisoDTO(p);
  }

  @PreAuthorize("isAuthenticated()")
  @Transactional(readOnly = false)
  public void deleteById(Long id) throws NotFoundException, OperationNotAllowed {
    Piso p = pisoDao.findById(id);
    if (p == null) throw new NotFoundException(id.toString(), Piso.class);
    UserDTOPrivate currenUser = userService.getCurrentUserWithAuthority();
    if (!currenUser.getId().equals(p.getAnunciante().getIdUsuario())) {
      throw new OperationNotAllowed("Current user does not match piso creator");
    }
    pisoDao.delete(p);
  }
}
