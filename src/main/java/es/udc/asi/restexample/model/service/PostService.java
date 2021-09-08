package es.udc.asi.restexample.model.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.asi.restexample.model.domain.Post;
import es.udc.asi.restexample.model.exception.NotFoundException;
import es.udc.asi.restexample.model.repository.PostDao;
import es.udc.asi.restexample.model.repository.TagDao;
import es.udc.asi.restexample.model.repository.UserDao;
import es.udc.asi.restexample.model.service.dto.PostDTO;

@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class PostService {

  @Autowired
  private PostDao postDAO;

  @Autowired
  private UserDao userDAO;

  @Autowired
  private TagDao tagDAO;

  public List<PostDTO> findAll() {
    return postDAO.findAll().stream().map(post -> new PostDTO(post)).collect(Collectors.toList());
  }

  public PostDTO findById(Long id) throws NotFoundException {
    Post post = postDAO.findById(id);
    if (post == null) {
      throw new NotFoundException("post with id " + id + " not found!");
    }
    return new PostDTO(postDAO.findById(id));
  }

  // Con estas anotaciones evitamos que usuarios no autorizados accedan a ciertas
  // funcionalidades
  @PreAuthorize("hasAuthority('ADMIN')")
  @Transactional(readOnly = false)
  public PostDTO create(PostDTO post) {
    Post bdPost = new Post(post.getTitle(), post.getBody());
    bdPost.setAuthor(userDAO.findById(post.getAuthor().getId()));
    if (post.getTags() != null) {
      post.getTags().forEach(tag -> {
        bdPost.getTags().add(tagDAO.findById(tag.getId()));
      });
    }
    postDAO.create(bdPost);
    return new PostDTO(bdPost);
  }

  @PreAuthorize("hasAuthority('ADMIN')")
  @Transactional(readOnly = false)
  public PostDTO update(PostDTO post) {
    Post bdPost = postDAO.findById(post.getId());
    bdPost.setTitle(post.getTitle());
    bdPost.setBody(post.getBody());
    bdPost.setAuthor(userDAO.findById(post.getAuthor().getId()));
    bdPost.getTags().clear();
    post.getTags().forEach(tag -> {
      bdPost.getTags().add(tagDAO.findById(tag.getId()));
    });
    postDAO.update(bdPost);
    return new PostDTO(bdPost);
  }

  @PreAuthorize("hasAuthority('ADMIN')")
  @Transactional(readOnly = false)
  public void deleteById(Long id) {
    postDAO.deleteById(id);
  }
}
