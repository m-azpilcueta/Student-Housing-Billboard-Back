package es.udc.asi.restexample.model.repository;

import es.udc.asi.restexample.model.domain.Universidad;
import es.udc.asi.restexample.model.repository.util.GenericDaoJpa;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UniversidadDaoJpa extends GenericDaoJpa implements UniversidadDao {

  @Override
  public Universidad findById(Long id) {
    return entityManager.find(Universidad.class, id);
  }

  @Override
  public List<Universidad> findAll() {
    return entityManager.createQuery("from Universidad ", Universidad.class).getResultList();
  }

  @Override
  public void create(Universidad universidad) {
    entityManager.persist(universidad);
  }
}
