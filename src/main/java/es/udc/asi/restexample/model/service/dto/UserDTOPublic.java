package es.udc.asi.restexample.model.service.dto;

import es.udc.asi.restexample.model.domain.User;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Comparator;
import java.util.stream.Collectors;

public class UserDTOPublic {
  private Long id;
  private String login;
  private String nombre;
  private String telefonoContacto;
  private String email;
  private boolean active = true;
  private EstudioDTO estudio;

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

    /*user.getPisos().forEach(p -> {
      this.pisos.add(new PisoDTO(p));
    });
    this.pisos.sort(Comparator.comparing(PisoDTO::getName));
    user.getFavoritos().forEach(fav -> {
      this.favoritos.add(new PisoDTO(fav));
    });
    this.favoritos.sort(Comparator.comparing(PisoDTO::getName));

     */
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

  /*

  public List<PisoDTO> getPisos() {
    return pisos;
  }

  public void setPisos(List<PisoDTO> pisos) {
    this.pisos = pisos;
  }

  public List<PisoDTO> getFavoritos() {
    return favoritos;
  }

  public void setFavoritos(List<PisoDTO> favoritos) {
    this.favoritos = favoritos;
  }

 */
}
