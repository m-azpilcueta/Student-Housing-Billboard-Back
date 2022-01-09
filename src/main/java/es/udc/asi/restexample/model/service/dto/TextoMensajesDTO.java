package es.udc.asi.restexample.model.service.dto;

public class TextoMensajesDTO {
  private Long id;
  private String texto;

  public TextoMensajesDTO() {

  }

  public TextoMensajesDTO(String texto) {
    this.texto = texto;
  }

  public TextoMensajesDTO(Long id, String texto) {
    this.id = id;
    this.texto = texto;
  }

  public String getTexto() {
    return texto;
  }

  public void setTexto(String texto) {
    this.texto = texto;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
