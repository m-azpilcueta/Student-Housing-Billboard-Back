package es.udc.asi.restexample.config;

import javax.annotation.PostConstruct;

import es.udc.asi.restexample.model.domain.Estudio;
import es.udc.asi.restexample.model.domain.Universidad;
import es.udc.asi.restexample.model.domain.User;
import es.udc.asi.restexample.model.domain.UserAuthority;
import es.udc.asi.restexample.model.repository.EstudioDao;
import es.udc.asi.restexample.model.repository.UniversidadDao;
import es.udc.asi.restexample.model.repository.UserDao;
import es.udc.asi.restexample.model.service.dto.UserDTOPrivate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import es.udc.asi.restexample.model.exception.UserLoginExistsException;
import es.udc.asi.restexample.model.service.UserService;

@Configuration
public class DatabaseLoader {
  private final Logger logger = LoggerFactory.getLogger(DatabaseLoader.class);

  @Autowired
  private UserDao userDao;

  @Autowired
  private EstudioDao estudioDao;

  @Autowired
  private UniversidadDao universidadDao;

  @Autowired
  private UserService userService;

  @Autowired
  private DatabaseLoader databaseLoader;

  @Autowired
  private PasswordEncoder passwordEncoder;

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

    Universidad conruna = new Universidad("Universidade de A Coruña");
    Universidad santiago = new Universidad("Universidade de Santiago de Compostela");
    Universidad orense = new Universidad("Universidade de Ourense");


    universidadDao.create(conruna);
    universidadDao.create(santiago);
    universidadDao.create(orense);

    Estudio gei_coruna = new Estudio("Grado en Ingenieria Informatica", universidadDao.findById(conruna.getIdUniversidad()));
    Estudio gei_santiago = new Estudio("Grado en Ingenieria Informatica", universidadDao.findById(santiago.getIdUniversidad()));

    estudioDao.create(gei_coruna);
    estudioDao.create(gei_santiago);

    User laura = new User ("laura", passwordEncoder.encode("laura"), "Laura Criado", "666333111", "laura.criado@udc.es",
      estudioDao.findById(gei_coruna.getIdEstudio()), UserAuthority.ADMIN);
    User martin = new User ("martin", passwordEncoder.encode("martin"), "Martin Azpilcueta", "666444222", "m.azpilcueta@udc.es",
      estudioDao.findById(gei_coruna.getIdEstudio()), UserAuthority.ADMIN);

    userDao.create(laura);
    userDao.create(martin);

  }
}
