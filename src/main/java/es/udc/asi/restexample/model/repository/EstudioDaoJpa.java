package es.udc.asi.restexample.model.repository;

import es.udc.asi.restexample.model.domain.Estudio;
import es.udc.asi.restexample.model.repository.util.GenericDaoJpa;
import org.springframework.stereotype.Repository;

@Repository
public class EstudioDaoJpa extends GenericDaoJpa implements EstudioDao{

  @Override
  public Estudio findById(Long id) {
    return entityManager.find(Estudio.class, id);
  }

  @Override
  public void create(Estudio estudio) {
    entityManager.persist(estudio);
  }
}
