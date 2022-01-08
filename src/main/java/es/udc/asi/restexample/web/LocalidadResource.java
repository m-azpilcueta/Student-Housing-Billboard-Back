package es.udc.asi.restexample.web;

import es.udc.asi.restexample.model.domain.Localidad;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.EnumSet;
import java.util.Set;

@RestController
@RequestMapping("/api/localidades")
public class LocalidadResource {
  @GetMapping
  public Set<Localidad> cargarLocalidad() {
    Set<Localidad> provincias = EnumSet.allOf(Localidad.class);
    provincias.remove(Localidad.DESCONOCIDO);
    return provincias;
  }
}
