package es.udc.asi.restexample.model.repository;

import es.udc.asi.restexample.model.domain.Imagen;

public interface ImagenDao {
  void create(Imagen imagen);
  Imagen find(Long id);
  void update(Imagen imagen);
  void delete(Imagen imagen);
}
