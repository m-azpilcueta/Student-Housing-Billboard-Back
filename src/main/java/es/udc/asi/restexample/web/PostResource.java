package es.udc.asi.restexample.web;

import java.io.IOException;
import java.util.List;

import javax.management.InstanceNotFoundException;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import es.udc.asi.restexample.model.domain.Post;
import es.udc.asi.restexample.model.exception.ModelException;
import es.udc.asi.restexample.model.exception.NotFoundException;
import es.udc.asi.restexample.model.exception.OperationNotAllowed;
import es.udc.asi.restexample.model.service.PostService;
import es.udc.asi.restexample.model.service.dto.ImageDTO;
import es.udc.asi.restexample.model.service.dto.PostDTO;
import es.udc.asi.restexample.web.exceptions.IdAndBodyNotMatchingOnUpdateException;
import es.udc.asi.restexample.web.exceptions.RequestBodyNotValidException;

@RestController
@RequestMapping("/api/posts")
public class PostResource {

  @Autowired
  private PostService postService;

  @GetMapping
  public List<PostDTO> findAll() {
    return postService.findAll();
  }

  @GetMapping("/{id}")
  public PostDTO findOne(@PathVariable Long id) throws NotFoundException {
    return postService.findById(id);
  }

  @GetMapping("/{id}/image")
  @ResponseStatus(HttpStatus.OK)
  public void getMovieImageById(@PathVariable Long id, HttpServletResponse response)
      throws InstanceNotFoundException, ModelException {
    ImageDTO image = postService.getPostImageById(id);

    try {
      response.setContentType(image.getMediaType());
      response.setHeader("Content-disposition", "filename=" + image.getFilename());
      IOUtils.copy(image.getInputStream(), response.getOutputStream());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @PostMapping("/{id}/image")
  @ResponseStatus(HttpStatus.OK)
  public void saveMovieImageById(@PathVariable Long id, @RequestParam MultipartFile file, HttpServletResponse response)
      throws InstanceNotFoundException, ModelException {

    postService.savePostImageById(id, file);
  }

  @PostMapping
  public PostDTO create(@RequestBody @Valid PostDTO post, Errors errors) throws RequestBodyNotValidException {
    if (errors.hasErrors()) {
      throw new RequestBodyNotValidException(errors);
    }

    return postService.create(post);
  }

  @PutMapping("/{id}")
  public PostDTO update(@PathVariable Long id, @RequestBody @Valid PostDTO post, Errors errors)
      throws IdAndBodyNotMatchingOnUpdateException, RequestBodyNotValidException {
    if (errors.hasErrors()) {
      throw new RequestBodyNotValidException(errors);
    }

    if (id != post.getId()) {
      throw new IdAndBodyNotMatchingOnUpdateException(Post.class);
    }
    return postService.update(post);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) throws NotFoundException, OperationNotAllowed {
    postService.deleteById(id);
  }

}
