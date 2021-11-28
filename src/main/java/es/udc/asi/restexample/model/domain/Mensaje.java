package es.udc.asi.restexample.model.domain;

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

  @ManyToOne
  @JoinColumn(name = "usuario", nullable = false)
  private User usuario;

  @OneToOne
  @JoinColumn(name = "pregunta")
  private Mensaje pregunta;

  @OneToOne(mappedBy = "pregunta")
  private Mensaje respuesta;
}
