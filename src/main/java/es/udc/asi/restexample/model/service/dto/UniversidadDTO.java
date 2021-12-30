package es.udc.asi.restexample.model.service.dto;

import es.udc.asi.restexample.model.domain.Universidad;

public class UniversidadDTO {

    private Long idUniversidad;

    private String nombreUniversidad;

    public UniversidadDTO(Universidad universidad) {
      this.idUniversidad = universidad.getIdUniversidad();
      this.nombreUniversidad = universidad.getNombre();
    }

  public Long getIdUniversidad() {
    return idUniversidad;
  }

  public void setIdUniversidad(Long idUniversidad) {
    this.idUniversidad = idUniversidad;
  }

  public String getNombreUniversidad() {
    return nombreUniversidad;
  }

  public void setNombreUniversidad(String nombreUniversidad) {
    this.nombreUniversidad = nombreUniversidad;
  }
}
