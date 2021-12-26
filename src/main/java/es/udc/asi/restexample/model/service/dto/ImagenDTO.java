package es.udc.asi.restexample.model.service.dto;

import java.io.InputStream;

public class ImagenDTO {
  private InputStream inputStream;
  private Long idImagen;
  private String nombre;
  private String path;

  public ImagenDTO(InputStream inputStream,String nombre, String path) {
    this.inputStream = inputStream;
    this.nombre = nombre;
    this.path = path;
  }

  public InputStream getInputStream() {
    return inputStream;
  }

  public Long getIdImagen() {
    return idImagen;
  }

  public String getNombre() {
    return nombre;
  }

  public String getPath() {
    return path;
  }
}
