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

  @Override
  public Piso findById(Long id) {
    List<Piso> pisos = entityManager.createQuery("from Piso p where p.idPiso=:id AND p.anunciante.active = TRUE", Piso.class).setParameter("id", id).getResultList();
    return pisos.size() != 0 ? pisos.get(0) : null;
  }

  @Override
  public void create(Piso piso) {
    entityManager.persist(piso);
  }
}
