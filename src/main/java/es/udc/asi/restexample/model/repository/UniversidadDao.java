package es.udc.asi.restexample.model.repository;


import es.udc.asi.restexample.model.domain.Universidad;

import java.util.List;

public interface UniversidadDao {

  Universidad findById(Long id);

  List<Universidad> findAll();

  void create(Universidad universidad);
}
