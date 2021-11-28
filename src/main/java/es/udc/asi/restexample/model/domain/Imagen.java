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

  @Column(unique = true)
  private String path;
}
