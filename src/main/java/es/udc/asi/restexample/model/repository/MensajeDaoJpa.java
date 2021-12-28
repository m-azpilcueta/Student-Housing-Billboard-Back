package es.udc.asi.restexample.model.repository;

import es.udc.asi.restexample.model.domain.Mensaje;
import es.udc.asi.restexample.model.repository.util.GenericDaoJpa;
import org.springframework.stereotype.Repository;

@Repository
public class MensajeDaoJpa extends GenericDaoJpa implements MensajeDao {
  @Override
  public void create(Mensaje mensaje) {
    entityManager.persist(mensaje);
  }
}
