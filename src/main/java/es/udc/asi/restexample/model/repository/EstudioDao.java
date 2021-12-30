package es.udc.asi.restexample.model.repository;

import es.udc.asi.restexample.model.domain.Estudio;
import es.udc.asi.restexample.model.domain.User;

import java.util.List;

public interface EstudioDao {

  Estudio findById(Long id);

  Estudio findByNombre(String nombre);

  List<Estudio> findAll();

  void create(Estudio estudio);

}
