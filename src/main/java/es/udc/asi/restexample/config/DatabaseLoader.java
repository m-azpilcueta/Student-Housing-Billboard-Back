package es.udc.asi.restexample.config;

import javax.annotation.PostConstruct;

import es.udc.asi.restexample.model.domain.*;
import es.udc.asi.restexample.model.repository.EstudioDao;
import es.udc.asi.restexample.model.repository.PisoDao;
import es.udc.asi.restexample.model.repository.UniversidadDao;
import es.udc.asi.restexample.model.repository.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import es.udc.asi.restexample.model.exception.UserLoginExistsException;

import java.time.LocalDate;

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
  private PisoDao pisoDao;

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
    Universidad vigo = new Universidad("Universidade de Vigo");

    universidadDao.create(conruna);
    universidadDao.create(santiago);
    universidadDao.create(vigo);

    Estudio gei_coruna = new Estudio("Grado en Ingenieria Informatica", universidadDao.findById(conruna.getIdUniversidad()));
    Estudio gec_coruna = new Estudio("Grado en Ingenieria de Caminos", universidadDao.findById(conruna.getIdUniversidad()));
    Estudio qui_coruna = new Estudio("Grado en Quimica", universidadDao.findById(conruna.getIdUniversidad()));
    Estudio gei_santiago = new Estudio("Grado en Ingenieria Informatica", universidadDao.findById(santiago.getIdUniversidad()));
    Estudio bio_santiago = new Estudio("Grado en Biología", universidadDao.findById(santiago.getIdUniversidad()));

    estudioDao.create(gei_coruna);
    estudioDao.create(gec_coruna);
    estudioDao.create(qui_coruna);
    estudioDao.create(gei_santiago);
    estudioDao.create(bio_santiago);

    //Usuarios administradores
    User laura = new User ("laura", passwordEncoder.encode("laura"), "Laura Criado", "666333111", "laura.criado@udc.es",
      estudioDao.findById(gei_coruna.getIdEstudio()), UserAuthority.ADMIN);
    User martin = new User ("martin", passwordEncoder.encode("martin"), "Martin Azpilcueta", "666444222", "m.azpilcueta@udc.es",
      estudioDao.findById(gei_coruna.getIdEstudio()), UserAuthority.ADMIN);

    userDao.create(laura);
    userDao.create(martin);

    User adrian = new User ("adrian.pazos", passwordEncoder.encode("adrian"), "Adrian Pazos Perez", "696358758", "adrian.pazos@usc.es",
      estudioDao.findById(gei_santiago.getIdEstudio()), UserAuthority.USER);
    User lorena = new User ("lorena.gg", passwordEncoder.encode("lorena"), "Lorena Garcia Gonzalez", "698136229", "lorena.gg@usc.es",
      estudioDao.findById(qui_coruna.getIdEstudio()), UserAuthority.USER);

    userDao.create(adrian);
    userDao.create(lorena);

    Piso piso_adrian = new Piso(true, "Rua das Rodas", "Piso en en el centro de Santiago", "15704", "El piso ha sido renovado recientemente",
      true, 225, "29", "Bajo A", 65, 3, 3 );

    piso_adrian.setAnunciante(userDao.findById(adrian.getIdUsuario()));
    piso_adrian.setLocalidad(Localidad.SANTIAGO);
    piso_adrian.setProvincia(Provincia.CORUÑA);

    Piso piso_lorena = new Piso(true, "Rúa Ría do Ferrol", "Piso en Arteixo amueblado", "15142", "Incluye gastos de comunidad y agua",
      true, 150, "13", "2 D", 50, 3, 2 );

    piso_lorena.setAnunciante(userDao.findById(lorena.getIdUsuario()));
    piso_lorena.setLocalidad(Localidad.ARTEIXO);
    piso_lorena.setProvincia(Provincia.CORUÑA);

    pisoDao.create(piso_adrian);
    pisoDao.create(piso_lorena);

  }
}
