package es.udc.asi.restexample.model.domain;

import javax.persistence.*;

@Entity
@Table(name = "theUni")
public class Universidad {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "universidad_generator")
  @SequenceGenerator(name = "universidad_generator", sequenceName = "uni_seq")
  private Long idUniversidad;

  private String nombre;

  public Universidad() {
  }

  public Long getIdUniversidad() {
    return idUniversidad;
  }

  public void setIdUniversidad(Long idUniversidad) {
    this.idUniversidad = idUniversidad;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }
}
