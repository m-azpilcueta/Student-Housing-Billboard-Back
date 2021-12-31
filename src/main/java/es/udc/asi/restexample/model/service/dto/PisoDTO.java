package es.udc.asi.restexample.model.service.dto;

import es.udc.asi.restexample.model.domain.Localidad;
import es.udc.asi.restexample.model.domain.Piso;
import es.udc.asi.restexample.model.domain.Provincia;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class PisoDTO {
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

  private String localidad;

  private String provincia;

  private int superficie;

  private int habitaciones;

  private int personas;

  private UserDTOPublic anunciante;

  private Set<ImagenDTO> imagenes = new HashSet<>();

  private Set<MensajeDTO> mensajes = new HashSet<>();

  public PisoDTO() {

  }

  public PisoDTO(Piso piso) {
    this.idPiso = piso.getIdPiso();
    this.amueblado = piso.isAmueblado();
    this.calle = piso.getCalle();
    this.nombre = piso.getNombre();
    this.codigoPostal = piso.getCodigoPostal();
    this.fechaPublicacion = piso.getFechaPublicacion();
    this.descripcion = piso.getDescripcion();
    this.disponible = piso.isDisponible();
    this.importe = piso.getImporte();
    this.numero = piso.getNumero();
    this.pisoLetra = piso.getPisoLetra();
    this.localidad = piso.getLocalidad().name();
    this.provincia = piso.getProvincia().name();
    this.superficie = piso.getSuperficie();
    this.habitaciones = piso.getHabitaciones();
    this.personas = piso.getPersonas();
    this.anunciante = new UserDTOPublic(piso.getAnunciante());
    if (piso.getImagenes() != null) {
      piso.getImagenes().forEach(i -> this.imagenes.add(new ImagenDTO(i.getIdImagen(), i.getNombre(), i.getPath(), i.isPortada())));
    }
    if (piso.getMensajes() != null) {
      piso.getMensajes().forEach(m -> this.mensajes.add(new MensajeDTO(m)));
    }
  }

  public PisoDTO(Long idPiso) {
    this.idPiso = idPiso;
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

  public String getLocalidad() {
    return localidad;
  }

  public void setLocalidad(String localidad) {
    this.localidad = localidad;
  }

  public String getProvincia() {
    return provincia;
  }

  public void setProvincia(String provincia) {
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

  public UserDTOPublic getAnunciante() {
    return anunciante;
  }

  public void setAnunciante(UserDTOPublic anunciante) {
    this.anunciante = anunciante;
  }

  public Set<ImagenDTO> getImagenes() {
    return imagenes;
  }

  public void setImagenes(Set<ImagenDTO> imagenes) {
    this.imagenes = imagenes;
  }

  public Set<MensajeDTO> getMensajes() {
    return mensajes;
  }

  public void setMensajes(Set<MensajeDTO> mensajes) {
    this.mensajes = mensajes;
  }
}
