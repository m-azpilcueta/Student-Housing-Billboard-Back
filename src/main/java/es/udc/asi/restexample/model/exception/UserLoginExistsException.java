package es.udc.asi.restexample.model.exception;

public class UserLoginExistsException extends ModelException {
  public UserLoginExistsException(String msg) {
    super(msg);
  }
}
