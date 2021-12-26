package es.udc.asi.restexample.model.domain;

import javax.persistence.*;

@Entity
@Table(name = "theImagen")
public class Imagen {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "imagen_generator")
  @SequenceGenerator(name = "imagen_generator", sequenceName = "imagen_seq")
  private Long imagen;

  private boolean portada;

  private String nombre;

  private String path;

  public Imagen() {
  }

  public Imagen(String nombre, String path) {
    this.nombre = nombre;
    this.path = path;
  }

  public Long getImagen() {
    return imagen;
  }

  public void setImagen(Long imagen) {
    this.imagen = imagen;
  }

  public boolean isPortada() {
    return portada;
  }

  public void setPortada(boolean portada) {
    this.portada = portada;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }
}
