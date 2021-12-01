package es.udc.asi.restexample.model.repository;

import es.udc.asi.restexample.model.domain.Piso;
import es.udc.asi.restexample.model.repository.util.GenericDaoJpa;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PisoDaoJpa extends GenericDaoJpa implements PisoDao {
  @Override
  public List<Piso> findAll() {
    return entityManager.createQuery("from Piso p where p.anunciante.active = TRUE", Piso.class).getResultList();
  }
}
