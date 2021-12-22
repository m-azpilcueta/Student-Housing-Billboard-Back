package es.udc.asi.restexample.model.service.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import es.udc.asi.restexample.model.domain.User;

public class UserDTOPrivate {
  private Long id;

  @NotEmpty
  @Size(min = 4)
  private String login;

  @NotEmpty
  @Size(min = 4)
  private String password;
  private String authority;

  @NotEmpty
  private String nombre;

  @NotEmpty
  private String telefonoContacto;

  @NotEmpty
  @Size(min = 4)
  private String email;

  public UserDTOPrivate() {
  }

  public UserDTOPrivate(User user) {
    this.id = user.getIdUsuario();
    this.login = user.getLogin();
    this.nombre = user.getNombre();
    this.telefonoContacto = user.getTelefonoContacto();
    this.email = user.getEmail();
    this.authority = user.getAuthority().name();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
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

  public String getAuthority() {
    return authority;
  }

  public void setAuthority(String authority) {
    this.authority = authority;
  }
}
