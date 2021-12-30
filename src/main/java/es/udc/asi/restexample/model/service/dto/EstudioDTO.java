package es.udc.asi.restexample.model.service.dto;

import es.udc.asi.restexample.model.domain.Estudio;

public class EstudioDTO {

  private Long idEstudio;

  private String nombreEstudio;

  private UniversidadDTO universidad;

  public EstudioDTO(){

  }

  public EstudioDTO(Estudio estudio) {
    this.idEstudio = estudio.getIdEstudio();
    this.nombreEstudio = estudio.getNombre();
    this.universidad = new UniversidadDTO(estudio.getUniversidad());
  }

  public Long getIdEstudio() {
    return idEstudio;
  }

  public void setIdEstudio(Long idEstudio) {
    this.idEstudio = idEstudio;
  }

  public String getNombreEstudio() {
    return nombreEstudio;
  }

  public void setNombreEstudio(String nombreEstudio) {
    this.nombreEstudio = nombreEstudio;
  }

  public UniversidadDTO getUniversidad() {
    return universidad;
  }

  public void setUniversidad(UniversidadDTO universidad) {
    this.universidad = universidad;
  }
}
