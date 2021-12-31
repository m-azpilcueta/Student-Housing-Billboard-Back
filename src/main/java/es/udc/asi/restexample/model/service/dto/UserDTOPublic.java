package es.udc.asi.restexample.model.service.dto;

import es.udc.asi.restexample.model.domain.User;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class UserDTOPublic {
  private Long id;
  private String login;
  private String nombre;
  private String telefonoContacto;
  private String email;
  private boolean active = true;
  private EstudioDTO estudio;

  private Set<PisoDTO> favoritos = new HashSet<>();
 /* private List<PisoDTO> pisos = new ArrayList<>();
  private List<PisoDTO> favoritos = new ArrayList<>();
*/
  public UserDTOPublic() {
  }

  public UserDTOPublic(User user) {
    this.id = user.getIdUsuario();
    this.login = user.getLogin();
    this.nombre = user.getNombre();
    this.telefonoContacto = user.getTelefonoContacto();
    this.email = user.getEmail();
    this.estudio = new EstudioDTO(user.getEstudio());
    this.setActive(user.isActive());
    this.setFavoritos(user.getFavoritos().stream().map(fav -> new PisoDTO(fav)).collect(Collectors.toSet()));
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

  public Set<PisoDTO> getFavoritos() {
    return favoritos;
  }

  public void setFavoritos(Set<PisoDTO> favoritos) {
    this.favoritos = favoritos;
  }

}
