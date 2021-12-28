package es.udc.asi.restexample.model.service.dto;

import es.udc.asi.restexample.model.domain.Mensaje;
import org.w3c.dom.Text;

import java.time.LocalDateTime;

public class MensajeDTO {
  private LocalDateTime fecha;
  private TextoMensajesDTO pregunta;
  private TextoMensajesDTO respuesta;
  private String texto;
  private UserDTOPublic usuario;

  public MensajeDTO(Mensaje mensaje) {
    this.fecha = mensaje.getFecha();
    this.texto = mensaje.getTexto();
    this.usuario = new UserDTOPublic(mensaje.getUsuario());
    if (mensaje.getPregunta() == null) this.pregunta = null;
    else this.pregunta = new TextoMensajesDTO(mensaje.getPregunta().getTexto());
    if (mensaje.getRespuesta() == null) this.respuesta = null;
    else this.respuesta = new TextoMensajesDTO(mensaje.getRespuesta().getTexto());
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

  public UserDTOPublic getUsuario() {
    return usuario;
  }

  public void setUsuario(UserDTOPublic usuario) {
    this.usuario = usuario;
  }
}
