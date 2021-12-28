package es.udc.asi.restexample.model.domain;

import es.udc.asi.restexample.model.service.dto.TextoMensajesDTO;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "theMensaje")
public class Mensaje {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mensaje_generator")
  @SequenceGenerator(name = "mensaje_generator", sequenceName = "mensaje_seq")
  private Long idMensaje;

  private LocalDateTime fecha;

  private String texto;

  @ManyToOne (fetch =  FetchType.LAZY)
  @JoinColumn(name = "usuario", nullable = false)
  private User usuario;

  @OneToOne (cascade = CascadeType.REMOVE)
  @JoinColumn(name = "pregunta")
  private Mensaje pregunta;

  @OneToOne(mappedBy = "pregunta")
  private Mensaje respuesta;

  public Mensaje() {
  }

  public Mensaje(TextoMensajesDTO texto) {
    this.fecha = LocalDateTime.now();
    this.texto = texto.getTexto();
  }

  public Long getIdMensaje() {
    return idMensaje;
  }

  public void setIdMensaje(Long idMensaje) {
    this.idMensaje = idMensaje;
  }

  public LocalDateTime getFecha() {
    return fecha;
  }

  public void setFecha(LocalDateTime fecha) {
    this.fecha = fecha;
  }

  public String getTexto() {
    return texto;
  }

  public void setTexto(String texto) {
    this.texto = texto;
  }

  public User getUsuario() {
    return usuario;
  }

  public void setUsuario(User usuario) {
    this.usuario = usuario;
  }

  public Mensaje getPregunta() {
    return pregunta;
  }

  public void setPregunta(Mensaje pregunta) {
    this.pregunta = pregunta;
  }

  public Mensaje getRespuesta() {
    return respuesta;
  }

  public void setRespuesta(Mensaje respuesta) {
    this.respuesta = respuesta;
  }
}
