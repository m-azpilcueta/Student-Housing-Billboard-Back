package es.udc.asi.restexample.model.domain;

import javax.persistence.*;

@Entity
@Table(name = "theEstudio")
public class Estudio {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "estudio_generator")
  @SequenceGenerator(name = "estudio_generator", sequenceName = "estud_seq")
  private Long idEstudio;

  private String nombre;

  @ManyToOne (fetch =  FetchType.EAGER)
  @JoinColumn(name = "id_universidad", nullable = false)
  private Universidad idUniv;

  public Estudio() {
  }

  public Long getIdEstudio() {
    return idEstudio;
  }

  public void setIdEstudio(Long idEstudio) {
    this.idEstudio = idEstudio;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public Universidad getIdUniv() {
    return idUniv;
  }

  public void setIdUniv(Universidad idUniv) {
    this.idUniv = idUniv;
  }
}
