package es.udc.asi.restexample.web.exceptions;

public class RequestBodyNotValidException extends ResourceException {
  public RequestBodyNotValidException(String errorMsg) {
    super(errorMsg);
  }
}
