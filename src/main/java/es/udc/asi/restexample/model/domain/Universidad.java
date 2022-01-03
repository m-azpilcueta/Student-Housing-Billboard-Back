package es.udc.asi.restexample.model.domain;

import es.udc.asi.restexample.model.service.dto.UniversidadDTO;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "theUni")
public class Universidad {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "universidad_generator")
  @SequenceGenerator(name = "universidad_generator", sequenceName = "uni_seq")
  private Long idUniversidad;

  private String nombre;

  @OneToMany(mappedBy = "universidad")
  private Set<Estudio> estudios;

  public Universidad() {
  }

  public Universidad(String nombre) {
    this.nombre = nombre;
  }

  public Universidad(UniversidadDTO universidadDTO) {
    this.idUniversidad = universidadDTO.getIdUniversidad();
    this.nombre = universidadDTO.getNombreUniversidad();
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

  public Set<Estudio> getEstudios() {
    return estudios;
  }

  public void setEstudios(Set<Estudio> estudios) {
    this.estudios = estudios;
  }
}
