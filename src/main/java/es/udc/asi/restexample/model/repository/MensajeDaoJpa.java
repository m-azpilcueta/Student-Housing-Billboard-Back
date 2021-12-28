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

  @Override
  public Mensaje find(Long id) {
   return entityManager.find(Mensaje.class, id);
  }

  @Override
  public void update(Mensaje mensaje) {
    entityManager.merge(mensaje);
  }
}
