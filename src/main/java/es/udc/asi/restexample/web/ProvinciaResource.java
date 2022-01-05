package es.udc.asi.restexample.web;

import es.udc.asi.restexample.model.domain.Provincia;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.EnumSet;
import java.util.Set;

@RestController
@RequestMapping("/api/provincias")
public class ProvinciaResource {
  @GetMapping
  public Set<Provincia> cargarProvincias() {
     Set<Provincia> provincias = EnumSet.allOf(Provincia.class);
     provincias.remove(Provincia.DESCONOCIDO);
     return provincias;
  }
}
