package es.udc.asi.restexample.model.repository;

import es.udc.asi.restexample.model.domain.Imagen;
import es.udc.asi.restexample.model.repository.util.GenericDaoJpa;
import org.springframework.stereotype.Repository;

@Repository
public class ImagenDaoJpa extends GenericDaoJpa implements ImagenDao {
  @Override
  public void create(Imagen imagen) {
    entityManager.persist(imagen);
  }

  @Override
  public Imagen find(Long id) {
    return entityManager.find(Imagen.class, id);
  }

  @Override
  public void update(Imagen imagen) {
    entityManager.merge(imagen);
  }

  @Override
  public void delete(Imagen imagen) {
    entityManager.remove(imagen);
  }
}
