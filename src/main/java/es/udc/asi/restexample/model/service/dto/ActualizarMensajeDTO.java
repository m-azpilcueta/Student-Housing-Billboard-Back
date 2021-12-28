package es.udc.asi.restexample.model.service.dto;

public class ActualizarMensajeDTO {
  private Long idMensaje;
  private String texto;

  public ActualizarMensajeDTO() {

  }

  public Long getIdMensaje() {
    return idMensaje;
  }

  public void setIdMensaje(Long idMensaje) {
    this.idMensaje = idMensaje;
  }

  public String getTexto() {
    return texto;
  }

  public void setTexto(String texto) {
    this.texto = texto;
  }
}
