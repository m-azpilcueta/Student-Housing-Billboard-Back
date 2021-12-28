package es.udc.asi.restexample.model.service.dto;

public class TextoMensajesDTO {
  private String texto;

  public TextoMensajesDTO() {

  }

  public TextoMensajesDTO(String texto) {
    this.texto = texto;
  }

  public String getTexto() {
    return texto;
  }

  public void setTexto(String texto) {
    this.texto = texto;
  }
}
