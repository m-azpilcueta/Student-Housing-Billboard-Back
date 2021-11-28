package es.udc.asi.restexample.model.domain;

import javax.persistence.*;

@Entity
@Table(name = "theEstudio")
public class Estudio {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "estudio_generator")
  @SequenceGenerator(name = "estudio_generator", sequenceName = "estud_seq")
  private Long numEstudio;

  private String nombre;

  @ManyToOne
  @JoinColumn(nullable = false)
  private Universidad idUniv;

  public Long getNumEstudio() {
    return numEstudio;
  }

  public void setNumEstudio(Long numEstudio) {
    this.numEstudio = numEstudio;
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
