package es.udc.asi.restexample.model.service;

import java.util.List;
import java.util.stream.Collectors;

import es.udc.asi.restexample.model.domain.*;
import es.udc.asi.restexample.model.repository.EstudioDao;
import es.udc.asi.restexample.model.repository.UniversidadDao;
import es.udc.asi.restexample.model.service.dto.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.asi.restexample.model.exception.NotFoundException;
import es.udc.asi.restexample.model.exception.OperationNotAllowed;
import es.udc.asi.restexample.model.exception.UserLoginExistsException;
import es.udc.asi.restexample.model.repository.UserDao;
import es.udc.asi.restexample.security.SecurityUtils;

@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class UserService {

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private UserDao userDAO;

  @Autowired
  private EstudioDao estudioDAO;

  @Autowired
  private UniversidadDao universidadDAO;

  @PreAuthorize("hasAuthority('ADMIN')")
  public List<UserDTOPublic> findAll() {
    return userDAO.findAll().stream().map(user -> new UserDTOPublic(user)).collect(Collectors.toList());
  }

  public UserDTOPublic findById(Long id) throws NotFoundException {
    User user = userDAO.findById(id);
    if (user == null) {
      throw new NotFoundException(id.toString(), User.class);
    }
    return new UserDTOPublic(userDAO.findById(id));
  }

  @PreAuthorize("isAuthenticated()")
  @Transactional(readOnly = false)
  public UserDTOPrivate update(UserDTOPrivate user) throws NotFoundException {
    User bdUser = userDAO.findById(user.getId());

    if (bdUser == null) {
      throw new NotFoundException(user.getId().toString(), User.class);
    }

    if (estudioDAO.findById(user.getEstudio().getIdEstudio()) == null) {
      throw new NotFoundException(user.getEstudio().getIdEstudio().toString(), Estudio.class);
    }

    String encryptedPassword = passwordEncoder.encode(user.getPassword());

    bdUser.setLogin(user.getLogin());
    bdUser.setContrasena(encryptedPassword);
    bdUser.setNombre(user.getNombre());
    bdUser.setTelefonoContacto(user.getTelefonoContacto());
    bdUser.setEmail(user.getEmail());
    bdUser.setEstudio(estudioDAO.findById(user.getEstudio().getIdEstudio()));

    userDAO.update(bdUser);
    return new UserDTOPrivate(bdUser);
  }

  @Transactional(readOnly = false)
  public void registerUser(UserDTOPrivate account) throws UserLoginExistsException, NotFoundException {
    registerUser(account, false);
  }

  @Transactional(readOnly = false)
  public void registerUser(UserDTOPrivate account, boolean isAdmin) throws UserLoginExistsException, NotFoundException {
    if (userDAO.findByLogin(account.getLogin()) != null) {
      throw new UserLoginExistsException(account.getLogin());
    }

    User user = new User( );
    String encryptedPassword = passwordEncoder.encode(account.getPassword());

    user.setLogin(account.getLogin());
    user.setContrasena(encryptedPassword);
    user.setNombre(account.getNombre());
    user.setTelefonoContacto(account.getTelefonoContacto());
    user.setEmail(account.getEmail());

    if (estudioDAO.findById(account.getEstudio().getIdEstudio()) == null) {
      throw new NotFoundException(account.getEstudio().getIdEstudio().toString(), Estudio.class);
    }

    user.setEstudio(estudioDAO.findById(account.getEstudio().getIdEstudio()));

    user.setAuthority(UserAuthority.USER);
    if (isAdmin) {
      user.setAuthority(UserAuthority.ADMIN);
    }

    /*if (account.getEstudio().getIdEstudio() == null ) {
      EstudioDTO actualizado = createEstudio(account.getEstudio());
      user.setEstudio(estudioDAO.findById(actualizado.getIdEstudio()));
    } else {
      user.setEstudio(estudioDAO.findById(account.getEstudio().getIdEstudio()));
    }*/

    userDAO.create(user);
  }

  @PreAuthorize("hasAuthority('ADMIN')")
  @Transactional(readOnly = false)
  public UserDTOPublic updateActive(Long id, boolean active) throws NotFoundException, OperationNotAllowed {
    User user = userDAO.findById(id);
    if (user == null) {
      throw new NotFoundException(id.toString(), User.class);
    }

    UserDTOPrivate currentUser = getCurrentUserWithAuthority();
    if (currentUser.getId().equals(user.getIdUsuario())) {
      throw new OperationNotAllowed("The user cannot activate/deactive itself");
    }

    user.setActive(active);
    userDAO.update(user);
    return new UserDTOPublic(user);
  }

  public UserDTOPrivate getCurrentUserWithAuthority() {
    String currentUserLogin = SecurityUtils.getCurrentUserLogin();
    if (currentUserLogin != null) {
      return new UserDTOPrivate(userDAO.findByLogin(currentUserLogin));
    }
    return null;
  }

  public EstudioDTO createEstudio(EstudioDTO estudio) {

    Estudio new_estudio = new Estudio(estudio.getNombreEstudio());

    new_estudio.setUniversidad(universidadDAO.findById(estudio.getUniversidad().getIdUniversidad()));

    estudioDAO.create(new_estudio);

    return new EstudioDTO(new_estudio);
  }

  public List<UniversidadDTO> findAllUni() {
    return universidadDAO.findAll().stream().map(universidad -> new UniversidadDTO(universidad)).collect(Collectors.toList());
  }

  public List<EstudioDTO> findAllEstudios() {
    return estudioDAO.findAll().stream().map(estudio -> new EstudioDTO(estudio)).collect(Collectors.toList());
  }

}
