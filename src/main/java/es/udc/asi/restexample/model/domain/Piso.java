package es.udc.asi.restexample.model.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

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

  @Enumerated(EnumType.STRING)
  private Localidad localidad;

  @Enumerated(EnumType.STRING)
  private Provincia provincia;

  private int superficie;

  private int habitaciones;

  private int personas;

  @OneToMany (fetch =  FetchType.LAZY )
  @JoinTable(name = "thePisosImagenes",
  joinColumns = @JoinColumn(name = "id_piso", nullable = false),
  inverseJoinColumns = @JoinColumn(name = "id_imagen", nullable = false))
  private Set<Imagen> imagenes;

  @OneToMany (fetch =  FetchType.LAZY )
  @JoinTable(name = "thePisosMensajes",
  joinColumns = @JoinColumn(name = "id_piso", nullable = false),
  inverseJoinColumns = @JoinColumn(name = "id_mensaje", nullable = false))
  private Set<Mensaje> mensajes;

  @ManyToOne (fetch = FetchType.LAZY)
  @JoinColumn(name = "anunciante", nullable = false)
  private User anunciante;

  public Piso() {
  }

  public Piso(boolean amueblado, String calle, String nombre, String codigoPostal, String descripcion, boolean disponible, double importe, String numero, String pisoLetra, int superficie, int habitaciones, int personas) {
    this.amueblado = amueblado;
    this.calle = calle;
    this.nombre = nombre;
    this.codigoPostal = codigoPostal;
    this.fechaPublicacion = LocalDate.now();
    this.descripcion = descripcion;
    this.disponible = disponible;
    this.importe = importe;
    this.numero = numero;
    this.pisoLetra = pisoLetra;
    this.superficie = superficie;
    this.habitaciones = habitaciones;
    this.personas = personas;
  }

  public Long getIdPiso() {
    return idPiso;
  }

  public void setIdPiso(Long idPiso) {
    this.idPiso = idPiso;
  }

  public boolean isAmueblado() {
    return amueblado;
  }

  public void setAmueblado(boolean amueblado) {
    this.amueblado = amueblado;
  }

  public String getCalle() {
    return calle;
  }

  public void setCalle(String calle) {
    this.calle = calle;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getCodigoPostal() {
    return codigoPostal;
  }

  public void setCodigoPostal(String codigoPostal) {
    this.codigoPostal = codigoPostal;
  }

  public LocalDate getFechaPublicacion() {
    return fechaPublicacion;
  }

  public void setFechaPublicacion(LocalDate fechaPublicacion) {
    this.fechaPublicacion = fechaPublicacion;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public boolean isDisponible() {
    return disponible;
  }

  public void setDisponible(boolean disponible) {
    this.disponible = disponible;
  }

  public double getImporte() {
    return importe;
  }

  public void setImporte(double importe) {
    this.importe = importe;
  }

  public String getNumero() {
    return numero;
  }

  public void setNumero(String numero) {
    this.numero = numero;
  }

  public String getPisoLetra() {
    return pisoLetra;
  }

  public void setPisoLetra(String pisoLetra) {
    this.pisoLetra = pisoLetra;
  }

  public Localidad getLocalidad() {
    return localidad;
  }

  public void setLocalidad(Localidad localidad) {
    this.localidad = localidad;
  }

  public Provincia getProvincia() {
    return provincia;
  }

  public void setProvincia(Provincia provincia) {
    this.provincia = provincia;
  }

  public int getSuperficie() {
    return superficie;
  }

  public void setSuperficie(int superficie) {
    this.superficie = superficie;
  }

  public int getHabitaciones() {
    return habitaciones;
  }

  public void setHabitaciones(int habitaciones) {
    this.habitaciones = habitaciones;
  }

  public int getPersonas() {
    return personas;
  }

  public void setPersonas(int personas) {
    this.personas = personas;
  }

  public Set<Imagen> getImagenes() {
    return imagenes;
  }

  public void setImagenes(Set<Imagen> imagenes) {
    this.imagenes = imagenes;
  }

  public Set<Mensaje> getMensajes() {
    return mensajes;
  }

  public void setMensajes(Set<Mensaje> mensajes) {
    this.mensajes = mensajes;
  }

  public User getAnunciante() {
    return anunciante;
  }

  public void setAnunciante(User anunciante) {
    this.anunciante = anunciante;
  }
}
