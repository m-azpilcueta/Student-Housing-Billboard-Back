package es.udc.asi.restexample.model.repository;

import es.udc.asi.restexample.model.domain.Piso;
import es.udc.asi.restexample.model.repository.util.GenericDaoJpa;
import es.udc.asi.restexample.model.service.dto.PisoSortType;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class PisoDaoJpa extends GenericDaoJpa implements PisoDao {
  @Override
  public List<Piso> findAll(PisoSortType sort) {
    String queryStr = "select p from Piso p";
    String sortStr = "p.fechaPublicacion desc";
    if (sort != null) {
      switch (sort) {
        case MAS_RECIENTE:
          sortStr = "p.fechaPublicacion desc";
          break;
        case MENOS_RECIENTE:
          sortStr = "p.fechaPublicacion asc";
          break;
        case IMPORTE_ASCENDENTE:
          sortStr = "p.importe asc";
          break;
        case IMPORTE_DESCENDENTE:
          sortStr = "p.importe desc";
          break;
      }
    }
    queryStr += " order by " + sortStr;
    TypedQuery<Piso> query = entityManager.createQuery(queryStr, Piso.class);
    return query.getResultList();
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

  @Override
  public void update(Piso piso) {
    entityManager.merge(piso);
  }

  @Override
  public void delete(Piso piso) {
    entityManager.remove(piso);
  }
}
