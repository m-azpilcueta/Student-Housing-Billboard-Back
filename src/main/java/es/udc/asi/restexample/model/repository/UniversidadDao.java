package es.udc.asi.restexample.model.repository;


import es.udc.asi.restexample.model.domain.Universidad;

public interface UniversidadDao {

  Universidad findById(Long id);

  void create(Universidad universidad);
}
