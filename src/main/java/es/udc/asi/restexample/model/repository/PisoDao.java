package es.udc.asi.restexample.model.repository;

import es.udc.asi.restexample.model.domain.Piso;

import java.util.List;

public interface PisoDao {
  List<Piso> findAll();

  Piso findById(Long id);

  void create(Piso piso);

  void update(Piso piso);

  void delete(Piso piso);
}
