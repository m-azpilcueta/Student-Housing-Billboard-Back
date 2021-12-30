package es.udc.asi.restexample.model.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "theEstudio")
public class Estudio {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "estudio_generator")
  @SequenceGenerator(name = "estudio_generator", sequenceName = "estud_seq")
  private Long idEstudio;

  @NotNull
  private String nombre;

  @ManyToOne (fetch =  FetchType.EAGER)
  @JoinColumn(name = "id_universidad", nullable = false)
  private Universidad universidad;

  public Estudio() {
  }

  public Estudio(String nombre) {
    this.nombre = nombre;
  }

  public Estudio(String nombre, Universidad universidad) {
    this(nombre);
    this.universidad = universidad;
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

  public Universidad getUniversidad() {
    return universidad;
  }

  public void setUniversidad(Universidad universidad) {
    this.universidad = universidad;
  }
}
