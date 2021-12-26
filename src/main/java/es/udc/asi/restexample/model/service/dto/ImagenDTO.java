package es.udc.asi.restexample.model.service.dto;

import java.io.InputStream;

public class ImagenDTO {
  private InputStream inputStream;
  private Long idImagen;
  private String nombre;
  private String path;
  private String mediaType;
  private boolean portada;

  public ImagenDTO(InputStream inputStream, String path) {
    this.inputStream = inputStream;
    this.path = path;
  }

  public ImagenDTO(Long idImagen, String nombre, String path, boolean portada) {
    this.idImagen = idImagen;
    this.nombre = nombre;
    this.path = path;
    this.portada = portada;
  }

  public ImagenDTO(InputStream inputStream, String path, String mediaType) {
    this.inputStream = inputStream;
    this.path = path;
    this.mediaType = mediaType;
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

  public String getMediaType() {
    return mediaType;
  }

  public void setMediaType(String mediaType) {
    this.mediaType = mediaType;
  }

  public boolean isPortada() {
    return portada;
  }

  public void setPortada(boolean portada) {
    this.portada = portada;
  }
}
