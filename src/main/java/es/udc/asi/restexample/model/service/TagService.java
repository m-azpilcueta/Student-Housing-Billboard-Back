package es.udc.asi.restexample.model.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.asi.restexample.model.domain.Post;
import es.udc.asi.restexample.model.domain.Tag;
import es.udc.asi.restexample.model.exception.NotFoundException;
import es.udc.asi.restexample.model.repository.PostDao;
import es.udc.asi.restexample.model.repository.TagDao;
import es.udc.asi.restexample.model.service.dto.TagDTO;

@Service
@Transactional(readOnly = true)
public class TagService {

  @Autowired
  private TagDao tagDAO;

  @Autowired
  private PostDao postDAO;

  public List<TagDTO> findAll() {
    return tagDAO.findAll().stream().sorted(Comparator.comparing(Tag::getName)).map(TagDTO::new)
        .collect(Collectors.toList());
  }

  @Transactional(readOnly = false)
  public void deleteById(Long id) throws NotFoundException {
    List<Post> posts = postDAO.findAllByTag(id);
    Tag theTag = tagDAO.findById(id);
    if (theTag == null) {
      throw new NotFoundException(id.toString(), Tag.class);
    }
    posts.forEach(post -> {
      post.getTags().remove(theTag);
      postDAO.update(post);
    });
    tagDAO.delete(theTag);
  }
}
