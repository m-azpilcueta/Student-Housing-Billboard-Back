package es.udc.asi.restexample.model.service.dto;

import es.udc.asi.restexample.model.domain.Mensaje;

import java.time.LocalDateTime;

public class MensajeDTO {
  private Long id;
  private LocalDateTime fecha;
  private TextoMensajesDTO pregunta;
  private TextoMensajesDTO respuesta;
  private String texto;
  private AnuncianteDTO usuario;

  public MensajeDTO(Mensaje mensaje) {
    this.id = mensaje.getIdMensaje();
    this.fecha = mensaje.getFecha();
    this.texto = mensaje.getTexto();
    this.usuario = new AnuncianteDTO(mensaje.getUsuario());
    if (mensaje.getPregunta() == null) this.pregunta = null;
    else this.pregunta = new TextoMensajesDTO(mensaje.getPregunta().getIdMensaje(), mensaje.getPregunta().getTexto());
    if (mensaje.getRespuesta() == null) this.respuesta = null;
    else this.respuesta = new TextoMensajesDTO(mensaje.getRespuesta().getIdMensaje(), mensaje.getRespuesta().getTexto());
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public LocalDateTime getFecha() {
    return fecha;
  }

  public void setFecha(LocalDateTime fecha) {
    this.fecha = fecha;
  }

  public TextoMensajesDTO getPregunta() {
    return pregunta;
  }

  public void setPregunta(TextoMensajesDTO pregunta) {
    this.pregunta = pregunta;
  }

  public TextoMensajesDTO getRespuesta() {
    return respuesta;
  }

  public void setRespuesta(TextoMensajesDTO respuesta) {
    this.respuesta = respuesta;
  }

  public String getTexto() {
    return texto;
  }

  public void setTexto(String texto) {
    this.texto = texto;
  }

  public AnuncianteDTO getUsuario() {
    return usuario;
  }

  public void setUsuario(AnuncianteDTO usuario) {
    this.usuario = usuario;
  }
}
