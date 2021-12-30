package es.udc.asi.restexample.model.repository;

import es.udc.asi.restexample.model.domain.Universidad;
import es.udc.asi.restexample.model.repository.util.GenericDaoJpa;
import org.springframework.stereotype.Repository;

@Repository
public class UniversidadDaoJpa extends GenericDaoJpa implements UniversidadDao {
  @Override
  public Universidad findById(Long id) {
    return entityManager.find(Universidad.class, id);
  }

  @Override
  public void create(Universidad universidad) {
    entityManager.persist(universidad);
  }
}
