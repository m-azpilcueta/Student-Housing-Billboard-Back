package es.udc.asi.restexample.model.domain;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "thePiso")
public class Piso {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "piso_generator")
  @SequenceGenerator(name = "piso_generator", sequenceName = "piso_seq")
  private Long idPiso;

  private boolean amueblado;

  private String calle;

  private String nombre;

  private String codigoPostal;

  private LocalDate fechaPublicacion;

  private String descripcion;

  private boolean disponible;

  private double importe;

  private String numero;

  private String pisoLetra;

  private Localidad localidad;

  private Provincia provincia;

  private int superficie;

  private int habitaciones;

  private int personas;
}
