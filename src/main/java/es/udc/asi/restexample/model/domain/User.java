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

  @ManyToOne
  @JoinColumn(nullable = false)
  private Estudio estudio;

  @ManyToMany
  @JoinTable(name = "theUsuarioFavoritos",
    joinColumns = @JoinColumn(name = "id_usuario", nullable = false),
    inverseJoinColumns = @JoinColumn(name = "id_piso", nullable = false))
  private Set<Piso> favoritos;

  @OneToMany(mappedBy = "anunciante")
  private Set<Piso> pisos;
}
