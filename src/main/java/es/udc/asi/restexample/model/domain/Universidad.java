package es.udc.asi.restexample.model.domain;

import javax.persistence.*;

@Entity
@Table(name = "theUni")
public class Universidad {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "universidad_generator")
  @SequenceGenerator(name = "universidad_generator", sequenceName = "uni_seq")
  private Long id;

  private String nombre;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }
}
