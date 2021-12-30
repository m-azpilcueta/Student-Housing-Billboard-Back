package es.udc.asi.restexample.model.repository;

import es.udc.asi.restexample.model.domain.Estudio;
import es.udc.asi.restexample.model.domain.Piso;
import es.udc.asi.restexample.model.domain.Universidad;
import es.udc.asi.restexample.model.domain.User;
import es.udc.asi.restexample.model.repository.util.GenericDaoJpa;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class EstudioDaoJpa extends GenericDaoJpa implements EstudioDao{

  @Override
  public Estudio findById(Long id) {
    return entityManager.find(Estudio.class, id);
  }

  @Override
  public Estudio findByNombre(String nombre) {
    List<Estudio> estudios = entityManager.createQuery("from Estudio e where e.nombre = :nombre", Estudio.class).setParameter("nombre", nombre).getResultList();
    return estudios.size() != 0 ? estudios.get(0) : null;
  }

  @Override
  public List<Estudio> findAll() {
    return entityManager.createQuery("from Estudio ", Estudio.class).getResultList();
  }

  @Override
  public void create(Estudio estudio) {
    entityManager.persist(estudio);
  }
}
