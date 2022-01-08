package es.udc.asi.restexample.model.service.dto;

import es.udc.asi.restexample.model.domain.User;

public class AnuncianteDTO {
  private Long id;
  private String login;
  private String nombre;
  private String telefonoContacto;
  private String email;
  private boolean active = true;
  private EstudioDTO estudio;

  public AnuncianteDTO() {

  }

  public AnuncianteDTO(User user) {
    this.id = user.getIdUsuario();
    this.login = user.getLogin();
    this.nombre = user.getNombre();
    this.telefonoContacto = user.getTelefonoContacto();
    this.email = user.getEmail();
    this.estudio = new EstudioDTO(user.getEstudio());
    this.setActive(user.isActive());
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

  public EstudioDTO getEstudio() {
    return estudio;
  }

  public void setEstudio(EstudioDTO estudio) {
    this.estudio = estudio;
  }
}
