package es.udc.asi.restexample.model.service.dto;

public class ActualizarImagenDTO {
  private Long idPiso;
  private Long idImagen;
  private boolean portada;

  public ActualizarImagenDTO(Long idPiso, Long idImagen, boolean portada) {
    this.idPiso = idPiso;
    this.idImagen = idImagen;
    this.portada = portada;
  }

  public Long getIdPiso() {
    return idPiso;
  }

  public void setIdPiso(Long idPiso) {
    this.idPiso = idPiso;
  }

  public Long getIdImagen() {
    return idImagen;
  }

  public void setIdImagen(Long idImagen) {
    this.idImagen = idImagen;
  }

  public boolean isPortada() {
    return portada;
  }

  public void setPortada(boolean portada) {
    this.portada = portada;
  }
}
