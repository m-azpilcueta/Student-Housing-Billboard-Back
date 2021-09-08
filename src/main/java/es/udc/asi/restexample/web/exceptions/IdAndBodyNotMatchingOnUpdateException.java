package es.udc.asi.restexample.web.exceptions;

import es.udc.asi.restexample.model.domain.Post;

public class IdAndBodyNotMatchingOnUpdateException extends ResourceException {
  public IdAndBodyNotMatchingOnUpdateException(Class<Post> clazz) {
    super("On update the sent item and the id on the request must be the same. Happening with " + clazz.getName());
  }
}
