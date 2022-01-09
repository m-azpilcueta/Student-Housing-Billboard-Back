package es.udc.asi.restexample.model.repository;

import es.udc.asi.restexample.model.domain.Mensaje;
import es.udc.asi.restexample.model.repository.util.GenericDaoJpa;
import org.springframework.stereotype.Repository;

import java.util.List;

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

  @Override
  public void delete(Mensaje mensaje) {
    entityManager.remove(mensaje);
  }

  @Override
  public Mensaje findRespuesta(Long idPregunta) {
    List<Mensaje> respuesta = entityManager.createQuery("from Mensaje m where pregunta.idMensaje = :idPregunta", Mensaje.class).setParameter("idPregunta", idPregunta).getResultList();
    return respuesta.size() != 0 ? respuesta.get(0) : null;
  }
}
