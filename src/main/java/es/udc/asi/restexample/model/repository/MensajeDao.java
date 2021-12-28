package es.udc.asi.restexample.model.repository;

import es.udc.asi.restexample.model.domain.Mensaje;

public interface MensajeDao {
  void create(Mensaje mensaje);
  Mensaje find(Long id);
  void update(Mensaje mensaje);
}
