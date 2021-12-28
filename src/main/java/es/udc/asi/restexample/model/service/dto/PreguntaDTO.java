package es.udc.asi.restexample.model.service.dto;

public class PreguntaDTO {
  private String texto;

  public PreguntaDTO() {

  }

  public PreguntaDTO(String texto) {
    this.texto = texto;
  }

  public String getTexto() {
    return texto;
  }

  public void setTexto(String texto) {
    this.texto = texto;
  }
}
