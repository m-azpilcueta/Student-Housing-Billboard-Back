package es.udc.asi.restexample.config;

import javax.annotation.PostConstruct;

import es.udc.asi.restexample.model.repository.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import es.udc.asi.restexample.model.exception.UserLoginExistsException;
import es.udc.asi.restexample.model.service.UserService;

@Configuration
public class DatabaseLoader {
  private final Logger logger = LoggerFactory.getLogger(DatabaseLoader.class);

  @Autowired
  private UserDao userDao;

  @Autowired
  private UserService userService;

  @Autowired
  private DatabaseLoader databaseLoader;

  /*
   * Para hacer que la carga de datos sea transacional, hay que cargar el propio
   * objeto como un bean y lanzar el método una vez cargado, ya que en el
   * PostConstruct (ni similares) se tienen en cuenta las anotaciones de
   * transaciones.
   */
  @PostConstruct
  public void init() {
    try {
      databaseLoader.loadData();
    } catch (UserLoginExistsException e) {
      logger.error(e.getMessage(), e);
    }
  }

  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public void loadData() throws UserLoginExistsException {

    //Cargamos los admninistradores de la app, que no tienen asociado ningun Estudio
    userService.registerUser("laura", "laura", "Laura Criado", "666333111", "laura.criado@udc.es", true);
    userService.registerUser("martin", "martin", "Martin Azpilcueta", "666444222", "m.azpilcueta@udc.es", true);

  }
}
