package es.udc.asi.restexample.model.domain;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "theUser")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_generator")
  @SequenceGenerator(name = "user_generator", sequenceName = "user_seq")
  private Long idUsuario;

  @Column(unique = true)
  private String login;

  private String contrasena;

  private String nombre;

  @Column(unique = true)
  private String telefonoContacto;

  @Column(unique = true)
  private String email;

  private boolean active = true;

  @Enumerated(EnumType.STRING)
  private UserAuthority authority;

  @ManyToOne (fetch =  FetchType.EAGER)
  @JoinColumn(nullable = false)
  private Estudio estudio;

  @ManyToMany (fetch =  FetchType.LAZY)
  @JoinTable(name = "theUsuarioFavoritos",
    joinColumns = @JoinColumn(name = "id_usuario", nullable = false),
    inverseJoinColumns = @JoinColumn(name = "id_piso", nullable = false))
  private Set<Piso> favoritos;

  @OneToMany(mappedBy = "anunciante",fetch =  FetchType.LAZY, cascade= CascadeType.REMOVE)
  private Set<Piso> pisos;

  public User() {
  }

  public Long getIdUsuario() {
    return idUsuario;
  }

  public void setIdUsuario(Long idUsuario) {
    this.idUsuario = idUsuario;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getContrasena() {
    return contrasena;
  }

  public void setContrasena(String contrasena) {
    this.contrasena = contrasena;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getTelefonoContacto() {
    return telefonoContacto;
  }

  public void setTelefonoContacto(String telefonoContacto) {
    this.telefonoContacto = telefonoContacto;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public UserAuthority getAuthority() {
    return authority;
  }

  public void setAuthority(UserAuthority authority) {
    this.authority = authority;
  }

  public Estudio getEstudio() {
    return estudio;
  }

  public void setEstudio(Estudio estudio) {
    this.estudio = estudio;
  }

  public Set<Piso> getFavoritos() {
    return favoritos;
  }

  public void setFavoritos(Set<Piso> favoritos) {
    this.favoritos = favoritos;
  }

  public Set<Piso> getPisos() {
    return pisos;
  }

  public void setPisos(Set<Piso> pisos) {
    this.pisos = pisos;
  }
}
