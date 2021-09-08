package es.udc.asi.restexample.model.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.asi.restexample.model.domain.User;
import es.udc.asi.restexample.model.domain.UserAuthority;
import es.udc.asi.restexample.model.exception.UserLoginExistsException;
import es.udc.asi.restexample.model.repository.UserDao;
import es.udc.asi.restexample.model.service.dto.UserDTOPrivate;
import es.udc.asi.restexample.model.service.dto.UserDTOPublic;
import es.udc.asi.restexample.security.SecurityUtils;

@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class UserService {

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private UserDao userDAO;

  public List<UserDTOPublic> findAll() {
    return userDAO.findAll().stream().map(user -> new UserDTOPublic(user)).collect(Collectors.toList());
  }

  @Transactional(readOnly = false)
  public void registerUser(String login, String password) throws UserLoginExistsException {
    registerUser(login, password, false);
  }

  @Transactional(readOnly = false)
  public void registerUser(String login, String password, boolean isAdmin) throws UserLoginExistsException {
    if (userDAO.findByLogin(login) != null) {
      throw new UserLoginExistsException("User login " + login + " already exists");
    }

    User user = new User();
    String encryptedPassword = passwordEncoder.encode(password);

    user.setLogin(login);
    user.setPassword(encryptedPassword);
    user.setAuthority(UserAuthority.USER);
    if (isAdmin) {
      user.setAuthority(UserAuthority.ADMIN);
    }

    userDAO.create(user);
  }

  public UserDTOPrivate getCurrentUserWithAuthority() {
    String currentUserLogin = SecurityUtils.getCurrentUserLogin();
    if (currentUserLogin != null) {
      return new UserDTOPrivate(userDAO.findByLogin(currentUserLogin));
    }
    return null;
  }
}
