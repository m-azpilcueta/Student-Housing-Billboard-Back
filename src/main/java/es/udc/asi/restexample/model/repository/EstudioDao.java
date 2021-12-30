package es.udc.asi.restexample.model.repository;

import es.udc.asi.restexample.model.domain.Estudio;

public interface EstudioDao {

  Estudio findById(Long id);

  void create(Estudio estudio);

}
