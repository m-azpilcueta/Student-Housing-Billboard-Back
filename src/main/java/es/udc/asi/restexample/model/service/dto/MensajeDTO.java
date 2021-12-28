package es.udc.asi.restexample.model.service.dto;

import es.udc.asi.restexample.model.domain.Mensaje;

import java.time.LocalDateTime;

public class MensajeDTO {
  private LocalDateTime fecha;
  private MensajeDTO pregunta;
  private MensajeDTO respuesta;
  private String texto;
  private UserDTOPublic usuario;

  public MensajeDTO(Mensaje mensaje) {
    this.fecha = mensaje.getFecha();
    this.texto = mensaje.getTexto();
    this.usuario = new UserDTOPublic(mensaje.getUsuario());
    if (mensaje.getPregunta() == null) this.pregunta = null;
    else this.pregunta = new MensajeDTO(mensaje.getPregunta());
    if (mensaje.getRespuesta() == null) this.respuesta = null;
    else this.respuesta = new MensajeDTO(mensaje.getRespuesta());
  }

  public LocalDateTime getFecha() {
    return fecha;
  }

  public void setFecha(LocalDateTime fecha) {
    this.fecha = fecha;
  }

  public MensajeDTO getPregunta() {
    return pregunta;
  }

  public void setPregunta(MensajeDTO pregunta) {
    this.pregunta = pregunta;
  }

  public MensajeDTO getRespuesta() {
    return respuesta;
  }

  public void setRespuesta(MensajeDTO respuesta) {
    this.respuesta = respuesta;
  }

  public String getTexto() {
    return texto;
  }

  public void setTexto(String texto) {
    this.texto = texto;
  }

  public UserDTOPublic getUsuario() {
    return usuario;
  }

  public void setUsuario(UserDTOPublic usuario) {
    this.usuario = usuario;
  }
}
